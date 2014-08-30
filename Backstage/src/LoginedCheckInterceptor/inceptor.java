package LoginedCheckInterceptor;
import java.io.Writer;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.era.util.LoginUser;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
public class inceptor extends AbstractInterceptor{

	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String intercept(ActionInvocation ar){
		String url = "<script language='javascript'>window.top.location.href='Backstage/WebRoot/Index.jsp'</script>";
		try {
			LoginUser lu=(LoginUser) ActionContext.getContext().getSession().get("LoginUser");
			String actionName = ar.getInvocationContext().getName();
			System.out.println(actionName);
			//HttpServletResponse res = ServletActionContext.getResponse();
			if(actionName == "adminLogin" || actionName.equals("adminLogin"))
			{
				return ar.invoke();
			}
			
			if(lu == null)
			{
				
				return Action.LOGIN;  
			}
			else
			{
				return ar.invoke();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
}
