package LoginedCheckInterceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginedCheckInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 7461940514014795143L;

	/** 拦截请求并进行登录有效性验证 */
	public String intercept(ActionInvocation ai) throws Exception {
		// 获取请求的action名称
		String actionName = ai.getInvocationContext().getName();
//		System.out.println(ai.getInvocationContext().getName());
		// 验证Session是否过期
//		if (!ServletActionContext.getRequest().isRequestedSessionIdValid()) {
//			// session过期,转向session过期提示页,最终跳转至登录页面
//			return "tologin";
//		} else {
			// 对登录与注销请求直接放行,不予拦截
			if (actionName.equals("chatRoomList") || actionName.equals("rolesList") || actionName.equals("pageFind")) {
				return ai.invoke();
			} else {
//				System.out.println(ActionContext.getContext().getSession().get("adminname"));
				Object adminname = ActionContext.getContext().getSession().get("adminname");
//				Object rolesaction = ActionContext.getContext().getSession().get("rolesaction");
//				Object rolesaction ServletActionContext.getRequest().getSession().getAttribute("rolesaction").toString();
//				String rolesaction = ServletActionContext.getRequest().getSession().getAttribute("rolesaction").toString();
//				String[] pro = rolesaction.split(",");
				// 验证是否已经登录
				if (null==adminname) {
					// 尚未登录,跳转至登录页面
					return "tologin";
				} else {
//					for (int i = 0; i < pro.length; i++) {
//						if (pro[i].equals(actionName)) {
//							// 验证通过,放行
//							return ai.invoke();
//						} 
//					}
				}
//			}
//		}
		
		// 验证失败,转向权限验证失败提示页
				return ai.invoke();
//		return "noprim";
	}
}
}