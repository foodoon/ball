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

import guda.ball.biz.TeamBiz;
import guda.ball.dao.domain.TeamDO;
import guda.ball.web.form.TeamEditForm;
import guda.ball.web.form.TeamForm;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;


@Controller
public class TeamAction {


    @Autowired
    private TeamBiz teamBiz;

    @RequestMapping(value = "team/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageId);
        modelMap.put("query",baseQuery);
        BizResult bizResult = teamBiz.list(baseQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "team/list.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "team/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, TeamEditForm teamEditForm,
        BindingResult result, Map<String,Object> model) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = teamBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            teamEditForm.initForm(((TeamDO)bizResult.data.get("teamDO")));
            return "team/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "team/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = teamBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "team/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "team/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, TeamForm teamForm,
        BindingResult result, Map<String,Object> model) {
        return "team/create.vm";
    }

    @RequestMapping(value = "team/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid  TeamForm teamForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "team/create.vm";
        }
        TeamDO teamDO = teamForm.toDO();
        BizResult bizResult = teamBiz.create(teamDO);
        if (bizResult.success) {
            return "redirect:/team/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "team/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid TeamEditForm teamEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "team/edit.vm";
        }
        TeamDO teamDO = teamEditForm.toDO();
        BizResult bizResult = teamBiz.update(teamDO);
        if (bizResult.success) {
            return "redirect:/team/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "team/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = teamBiz.delete(id);
        if (bizResult.success) {
            return "team/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
