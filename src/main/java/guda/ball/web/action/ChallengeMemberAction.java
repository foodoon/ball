package guda.ball.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import guda.ball.biz.ChallengeMemberBiz;
import guda.ball.dao.domain.ChallengeMemberDO;
import guda.ball.web.form.ChallengeMemberEditForm;
import guda.ball.web.form.ChallengeMemberForm;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;


@Controller
public class ChallengeMemberAction {


    @Autowired
    private ChallengeMemberBiz challengeMemberBiz;

    @RequestMapping(value = "challengeMember/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageId);
        modelMap.put("query",baseQuery);
        BizResult bizResult = challengeMemberBiz.list(baseQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "challengeMember/list.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "challengeMember/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, ChallengeMemberEditForm challengeMemberEditForm,
        BindingResult result, Map<String,Object> model) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = challengeMemberBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            challengeMemberEditForm.initForm(((ChallengeMemberDO)bizResult.data.get("challengeMemberDO")));
            return "challengeMember/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "challengeMember/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = challengeMemberBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "challengeMember/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "challengeMember/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, ChallengeMemberForm challengeMemberForm,
        BindingResult result, Map<String,Object> model) {
        return "challengeMember/create.vm";
    }

    @RequestMapping(value = "challengeMember/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid  ChallengeMemberForm challengeMemberForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "challengeMember/create.vm";
        }
        ChallengeMemberDO challengeMemberDO = challengeMemberForm.toDO();
        BizResult bizResult = challengeMemberBiz.create(challengeMemberDO);
        if (bizResult.success) {
            return "redirect:/challengeMember/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "challengeMember/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid ChallengeMemberEditForm challengeMemberEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "challengeMember/edit.vm";
        }
        ChallengeMemberDO challengeMemberDO = challengeMemberEditForm.toDO();
        BizResult bizResult = challengeMemberBiz.update(challengeMemberDO);
        if (bizResult.success) {
            return "redirect:/challengeMember/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "challengeMember/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = challengeMemberBiz.delete(id);
        if (bizResult.success) {
            return "challengeMember/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
