package guda.ball.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.alibaba.fastjson.JSON;
import guda.ball.biz.entity.OpenTemplateDemo;
import guda.ball.biz.query.CourtSiteQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import guda.ball.biz.CourtSiteBiz;
import guda.ball.dao.domain.CourtSiteDO;
import guda.ball.web.form.CourtSiteEditForm;
import guda.ball.web.form.CourtSiteForm;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;


@Controller
public class CourtSiteAction {


    @Autowired
    private CourtSiteBiz courtSiteBiz;

    @RequestMapping(value = "courtSite/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        long courtId = RequestUtil.getLong(request, "id");
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        CourtSiteQuery courtSiteQuery = new CourtSiteQuery();
        courtSiteQuery.setPageNo(pageId);
        courtSiteQuery.setCourtId(courtId);
        modelMap.put("query",courtSiteQuery);
        BizResult bizResult = courtSiteBiz.list(courtSiteQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "courtSite/list.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "courtSite/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, CourtSiteEditForm courtSiteEditForm,
        BindingResult result, Map<String,Object> model) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = courtSiteBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            courtSiteEditForm.initForm(((CourtSiteDO)bizResult.data.get("courtSiteDO")));
            return "courtSite/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "courtSite/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = courtSiteBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "courtSite/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "courtSite/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, CourtSiteForm courtSiteForm,
        BindingResult result, Map<String,Object> model) {
        long courtId = RequestUtil.getLong(request, "courtId");
        courtSiteForm.setCourtId(courtId);
        OpenTemplateDemo  openTemplateDemo = new OpenTemplateDemo();
        openTemplateDemo.init();
        courtSiteForm.setOpenTemplate(JSON.toJSONString(openTemplateDemo.getList()));
        modelMap.addAttribute("openInfo",openTemplateDemo.getList());
        return "courtSite/create.vm";
    }

    @RequestMapping(value = "courtSite/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid  CourtSiteForm courtSiteForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "courtSite/create.vm";
        }
        CourtSiteDO courtSiteDO = courtSiteForm.toDO();
        BizResult bizResult = courtSiteBiz.create(courtSiteDO);
        if (bizResult.success) {
            return "redirect:/courtSite/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "courtSite/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid CourtSiteEditForm courtSiteEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "courtSite/edit.vm";
        }
        CourtSiteDO courtSiteDO = courtSiteEditForm.toDO();
        BizResult bizResult = courtSiteBiz.update(courtSiteDO);
        if (bizResult.success) {
            return "redirect:/courtSite/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "courtSite/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = courtSiteBiz.delete(id);
        if (bizResult.success) {
            return "courtSite/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
