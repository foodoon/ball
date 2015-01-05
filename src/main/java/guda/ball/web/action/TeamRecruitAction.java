package guda.ball.web.action;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

import guda.ball.biz.TeamRecruitBiz;
import guda.ball.dao.domain.TeamRecruitDO;
import guda.ball.web.form.TeamRecruitEditForm;
import guda.ball.web.form.TeamRecruitForm;
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
public class TeamRecruitAction {


    @Autowired
    private TeamRecruitBiz teamRecruitBiz;

    @RequestMapping(value = "teamRecruit/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageId);
        modelMap.put("query",baseQuery);
        BizResult bizResult = teamRecruitBiz.list(baseQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "teamRecruit/list.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "teamRecruit/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, TeamRecruitEditForm teamRecruitEditForm,
        BindingResult result, Map<String,Object> model) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = teamRecruitBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            teamRecruitEditForm.initForm(((TeamRecruitDO)bizResult.data.get("teamRecruitDO")));
            return "teamRecruit/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "teamRecruit/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = teamRecruitBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "teamRecruit/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "teamRecruit/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, TeamRecruitForm teamRecruitForm,
        BindingResult result, Map<String,Object> model) {
        return "teamRecruit/create.vm";
    }

    @RequestMapping(value = "teamRecruit/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid TeamRecruitForm teamRecruitForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "teamRecruit/create.vm";
        }
        TeamRecruitDO teamRecruitDO = teamRecruitForm.toDO();
        BizResult bizResult = teamRecruitBiz.create(teamRecruitDO);
        if (bizResult.success) {
            return "redirect:/teamRecruit/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "teamRecruit/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid TeamRecruitEditForm teamRecruitEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "teamRecruit/edit.vm";
        }
        TeamRecruitDO teamRecruitDO = teamRecruitEditForm.toDO();
        BizResult bizResult = teamRecruitBiz.update(teamRecruitDO);
        if (bizResult.success) {
            return "redirect:/teamRecruit/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "teamRecruit/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = teamRecruitBiz.delete(id);
        if (bizResult.success) {
            return "redirect:/teamRecruit/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
