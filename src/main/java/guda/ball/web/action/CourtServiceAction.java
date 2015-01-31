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

import guda.ball.biz.CourtServiceBiz;
import guda.ball.dao.domain.CourtServiceDO;
import guda.ball.web.form.CourtServiceEditForm;
import guda.ball.web.form.CourtServiceForm;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;


@Controller
public class CourtServiceAction {


    @Autowired
    private CourtServiceBiz courtServiceBiz;

    @RequestMapping(value = "courtService/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageId);
        modelMap.put("query",baseQuery);
        BizResult bizResult = courtServiceBiz.list(baseQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "courtService/list.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "courtService/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, CourtServiceEditForm courtServiceEditForm,
        BindingResult result, Map<String,Object> model) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = courtServiceBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            courtServiceEditForm.initForm(((CourtServiceDO)bizResult.data.get("courtServiceDO")));
            return "courtService/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "courtService/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = courtServiceBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "courtService/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "courtService/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, CourtServiceForm courtServiceForm,
        BindingResult result, Map<String,Object> model) {
        return "courtService/create.vm";
    }

    @RequestMapping(value = "courtService/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid  CourtServiceForm courtServiceForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "courtService/create.vm";
        }
        CourtServiceDO courtServiceDO = courtServiceForm.toDO();
        BizResult bizResult = courtServiceBiz.create(courtServiceDO);
        if (bizResult.success) {
            return "redirect:/courtService/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "courtService/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid CourtServiceEditForm courtServiceEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "courtService/edit.vm";
        }
        CourtServiceDO courtServiceDO = courtServiceEditForm.toDO();
        BizResult bizResult = courtServiceBiz.update(courtServiceDO);
        if (bizResult.success) {
            return "redirect:/courtService/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "courtService/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = courtServiceBiz.delete(id);
        if (bizResult.success) {
            return "courtService/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
