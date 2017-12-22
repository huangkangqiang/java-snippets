package ssoDemo.sso;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;

public class MyValidationFilter extends Cas20ProxyReceivingTicketValidationFilter {
	
	@Override
	public void init() {
		System.out.println("======================init===================");
		super.init();
	}

	@Override
	public void destroy() {
		System.out.println("=====================destroy=================");
		super.destroy();
	}
	
	@Override
	protected void onSuccessfulValidation(HttpServletRequest request, HttpServletResponse response,
			Assertion assertion) {
		System.out.println("================onSuccessfulValidation=================");
		String name="";
		name=assertion.getPrincipal().getName();
		System.out.println("name:"+name);
		HttpSession session=request.getSession();
		Enumeration<String> enums=session.getAttributeNames();
		while(enums.hasMoreElements()){
			System.out.println(session.getAttribute(enums.nextElement()));
		}
		session.setAttribute("username", name);
		System.out.println("========================================");
		System.out.println(session.getAttribute("_const_cas_assertion_").toString());
		super.onSuccessfulValidation(request, response, assertion);
	}
}
