package com.example.demo;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;

import java.io.*;

/**
 * 将文件夹打成tar.gz包
 * 1)先把需要压缩的文件夹，打包成.tar文件
 * 2)使用gzip把.tar文件进行压缩（tar.gz）
 */
public class ArchiveApplication {

    public static void main(String[] args) {
        System.out.println("==========开始调用打包程序===========");
        try {
            if (args.length != 1) {
                throw new RuntimeException("参数不正确，请带上需要打包的文件夹路径！");
            }
            String entry = args[0];
            String archive = archive(entry);//生成tar包
            String path = compressArchive(archive);//生成gz包
            System.out.println("=========成功生成tar.gz包==========");
            File tarFile = new File(archive);
            if (tarFile.delete()) {
                System.out.println("=========成功删除tar包==========");
            } else {
                throw new RuntimeException("tar包删除失败！");
            }
            System.out.println("==========打包程序调用结束===========");
            System.out.println("包存放路径：" + path);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * 归档
     *
     * @param entry 需要打包的文件夹路径
     * @return tar包路径
     * @throws IOException IO异常
     */
    private static String archive(String entry) throws IOException {
        File file = new File(entry);
        TarArchiveOutputStream tos = new TarArchiveOutputStream(new FileOutputStream(file.getAbsolutePath() + ".tar"));
        String base = file.getName();
        if (file.isDirectory()) {
            archiveDir(file, tos, base);
        } else {
            archiveHandle(tos, file, base);
        }
        tos.close();
        return file.getAbsolutePath() + ".tar";
    }

    /**
     * 递归处理，准备好路径
     *
     * @param file     文件
     * @param tos      tos
     * @param basePath basePath
     * @throws IOException IO异常
     */
    private static void archiveDir(File file, TarArchiveOutputStream tos, String basePath) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            if (listFiles.length < 1) {
                TarArchiveEntry entry = new TarArchiveEntry(basePath + File.separator);
                tos.putArchiveEntry(entry);
                tos.closeArchiveEntry();
            } else {
                for (File fi : listFiles) {
                    if (fi.isDirectory()) {
                        archiveDir(fi, tos, basePath + File.separator + fi.getName());
                    } else {
                        archiveHandle(tos, fi, basePath);
                    }
                }
            }
        } else {
            throw new RuntimeException("文件夹不存在！");
        }
    }

    /**
     * 具体归档处理（文件）
     *
     * @param tos      tos
     * @param fi       fi
     * @param basePath basePath
     * @throws IOException IO异常
     */
    private static void archiveHandle(TarArchiveOutputStream tos, File fi, String basePath) throws IOException {
        TarArchiveEntry tEntry = new TarArchiveEntry(basePath + File.separator + fi.getName());
        tEntry.setSize(fi.length());

        tos.putArchiveEntry(tEntry);

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fi));

        byte[] buffer = new byte[1024];
        int read;
        while ((read = bis.read(buffer)) != -1) {
            tos.write(buffer, 0, read);
        }
        bis.close();
        //这里必须写，否则会失败
        tos.closeArchiveEntry();
    }

    /**
     * 把tar包压缩成gz
     *
     * @param path path
     * @return .gz包路径
     * @throws IOException IO异常
     */
    private static String compressArchive(String path) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));

        GzipCompressorOutputStream gcos = new GzipCompressorOutputStream(new BufferedOutputStream(new FileOutputStream(path + ".gz")));

        byte[] buffer = new byte[1024];
        int read;
        while ((read = bis.read(buffer)) != -1) {
            gcos.write(buffer, 0, read);
        }
        gcos.close();
        bis.close();
        return path + ".gz";
    }
}
