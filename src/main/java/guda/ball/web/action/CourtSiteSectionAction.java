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

import guda.ball.biz.CourtSiteSectionBiz;
import guda.ball.dao.domain.CourtSiteSectionDO;
import guda.ball.web.form.CourtSiteSectionEditForm;
import guda.ball.web.form.CourtSiteSectionForm;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;


@Controller
public class CourtSiteSectionAction {


    @Autowired
    private CourtSiteSectionBiz courtSiteSectionBiz;

    @RequestMapping(value = "courtSiteSection/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageId);
        modelMap.put("query",baseQuery);
        BizResult bizResult = courtSiteSectionBiz.list(baseQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "courtSiteSection/list.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "courtSiteSection/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, CourtSiteSectionEditForm courtSiteSectionEditForm,
        BindingResult result, Map<String,Object> model) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = courtSiteSectionBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            courtSiteSectionEditForm.initForm(((CourtSiteSectionDO)bizResult.data.get("courtSiteSectionDO")));
            return "courtSiteSection/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "courtSiteSection/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = courtSiteSectionBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "courtSiteSection/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "courtSiteSection/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, CourtSiteSectionForm courtSiteSectionForm,
        BindingResult result, Map<String,Object> model) {
        return "courtSiteSection/create.vm";
    }

    @RequestMapping(value = "courtSiteSection/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid  CourtSiteSectionForm courtSiteSectionForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "courtSiteSection/create.vm";
        }
        CourtSiteSectionDO courtSiteSectionDO = courtSiteSectionForm.toDO();
        BizResult bizResult = courtSiteSectionBiz.create(courtSiteSectionDO);
        if (bizResult.success) {
            return "redirect:/courtSiteSection/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "courtSiteSection/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid CourtSiteSectionEditForm courtSiteSectionEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "courtSiteSection/edit.vm";
        }
        CourtSiteSectionDO courtSiteSectionDO = courtSiteSectionEditForm.toDO();
        BizResult bizResult = courtSiteSectionBiz.update(courtSiteSectionDO);
        if (bizResult.success) {
            return "redirect:/courtSiteSection/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "courtSiteSection/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = courtSiteSectionBiz.delete(id);
        if (bizResult.success) {
            return "courtSiteSection/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
