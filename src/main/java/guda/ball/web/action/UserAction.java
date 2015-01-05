package guda.ball.web.action;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

import guda.ball.biz.UserBiz;
import guda.ball.dao.domain.UserDO;
import guda.ball.web.form.UserEditForm;
import guda.ball.web.form.UserForm;
import guda.tools.web.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;


@Controller
public class UserAction {


    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "user/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageId);

        modelMap.put("query",baseQuery);
        BizResult bizResult = userBiz.list(baseQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "user/list.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "user/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, UserEditForm userEditForm,
        BindingResult result, Map<String,Object> model) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = userBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            userEditForm.initForm(((UserDO)bizResult.data.get("userDO")));
            return "user/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "user/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = userBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "user/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "user/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, UserForm userForm,
        BindingResult result, Map<String,Object> model) {
        return "user/create.vm";
    }

    @RequestMapping(value = "user/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid UserForm userForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "user/create.vm";
        }
        UserDO userDO = userForm.toDO();
        BizResult bizResult = userBiz.create(userDO);
        if (bizResult.success) {
            return "redirect:/user/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "user/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid UserEditForm userEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "user/edit.vm";
        }
        UserDO userDO = userEditForm.toDO();
        BizResult bizResult = userBiz.update(userDO);
        if (bizResult.success) {
            return "redirect:/user/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "user/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = userBiz.delete(id);
        if (bizResult.success) {
            return "redirect:/user/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
