package LoginedCheckInterceptor;
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
		try {
			
			String actionName = ar.getInvocationContext().getName();
			Object hotelname=ActionContext.getContext().getSession().get("administrator");
			Object roleAction = ActionContext.getContext().getSession().get("roleAction");
			if(actionName.equals("UserLoginOut")){
				//不执行过滤器
				return ar.invoke();
			}
			if(actionName.equals("pageFind")){
				//不执行过滤器
				return ar.invoke();
			}
			if(actionName.equals("findAll")){
				//不执行过滤器
				return ar.invoke();
			}
			if(roleAction != null){
				Object[] roleString = ((String) roleAction).split(",");
				for (Object s : roleString) {
					if(actionName.equals(s)){
						return ar.invoke();
					}
				}
			}else {
				return "tologin";
			}
			
			if (null==hotelname)
			{	
				return "exception";
			}
			return "noprim";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tologin";
	}
}
