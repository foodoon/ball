package guda.ball.web.action;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
//import guda.tools.web.util.RequestUtil;
import guda.ball.biz.TeamApplyBiz;
import guda.ball.dao.domain.TeamApplyDO;
import guda.ball.web.form.TeamApplyEditForm;
import guda.ball.web.form.TeamApplyForm;
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
public class TeamApplyAction {


    @Autowired
    private TeamApplyBiz teamApplyBiz;

    @RequestMapping(value = "teamApply/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageId);
        modelMap.put("query",baseQuery);
        BizResult bizResult = teamApplyBiz.list(baseQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "teamApply/list.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "teamApply/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, TeamApplyEditForm teamApplyEditForm,
        BindingResult result, Map<String,Object> model) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = teamApplyBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            teamApplyEditForm.initForm(((TeamApplyDO)bizResult.data.get("teamApplyDO")));
            return "teamApply/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "teamApply/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = teamApplyBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "teamApply/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "teamApply/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, TeamApplyForm teamApplyForm,
        BindingResult result, Map<String,Object> model) {
        return "teamApply/create.vm";
    }

    @RequestMapping(value = "teamApply/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid TeamApplyForm teamApplyForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "teamApply/create.vm";
        }
        TeamApplyDO teamApplyDO = teamApplyForm.toDO();
        BizResult bizResult = teamApplyBiz.create(teamApplyDO);
        if (bizResult.success) {
            return "redirect:/teamApply/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "teamApply/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid TeamApplyEditForm teamApplyEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "teamApply/edit.vm";
        }
        TeamApplyDO teamApplyDO = teamApplyEditForm.toDO();
        BizResult bizResult = teamApplyBiz.update(teamApplyDO);
        if (bizResult.success) {
            return "redirect:/teamApply/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "teamApply/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = teamApplyBiz.delete(id);
        if (bizResult.success) {
            return "redirect:/teamApply/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
