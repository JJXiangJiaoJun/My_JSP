package Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import Form.HelloForm;;
public class HelloAction extends Action {
	//处理表单的逻辑层，需要重写execute函数
@Override
public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	// TODO Auto-generated method stub
	
	//取出对应的Action表单
	HelloForm helloForm = (HelloForm) form;
	
	//假设有成功失败页面
	if(helloForm.getUsername()==null||helloForm.getUsername().trim().length()==0)
	{
		//返回输入页面
		return  mapping.getInputForward();
	}
	
	//返回成功界面
	return mapping.findForward("success");
}
	
}
