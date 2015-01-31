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

import guda.ball.biz.TeamMemberBiz;
import guda.ball.dao.domain.TeamMemberDO;
import guda.ball.web.form.TeamMemberEditForm;
import guda.ball.web.form.TeamMemberForm;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;


@Controller
public class TeamMemberAction {


    @Autowired
    private TeamMemberBiz teamMemberBiz;

    @RequestMapping(value = "teamMember/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageId);
        modelMap.put("query",baseQuery);
        BizResult bizResult = teamMemberBiz.list(baseQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "teamMember/list.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "teamMember/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, TeamMemberEditForm teamMemberEditForm,
        BindingResult result, Map<String,Object> model) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = teamMemberBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            teamMemberEditForm.initForm(((TeamMemberDO)bizResult.data.get("teamMemberDO")));
            return "teamMember/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "teamMember/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = teamMemberBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "teamMember/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "teamMember/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, TeamMemberForm teamMemberForm,
        BindingResult result, Map<String,Object> model) {
        return "teamMember/create.vm";
    }

    @RequestMapping(value = "teamMember/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid  TeamMemberForm teamMemberForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "teamMember/create.vm";
        }
        TeamMemberDO teamMemberDO = teamMemberForm.toDO();
        BizResult bizResult = teamMemberBiz.create(teamMemberDO);
        if (bizResult.success) {
            return "redirect:/teamMember/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "teamMember/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid TeamMemberEditForm teamMemberEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "teamMember/edit.vm";
        }
        TeamMemberDO teamMemberDO = teamMemberEditForm.toDO();
        BizResult bizResult = teamMemberBiz.update(teamMemberDO);
        if (bizResult.success) {
            return "redirect:/teamMember/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "teamMember/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = teamMemberBiz.delete(id);
        if (bizResult.success) {
            return "teamMember/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
