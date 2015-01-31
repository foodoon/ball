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

import guda.ball.biz.CourtApplyBiz;
import guda.ball.dao.domain.CourtApplyDO;
import guda.ball.web.form.CourtApplyEditForm;
import guda.ball.web.form.CourtApplyForm;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;


@Controller
public class CourtApplyAction {


    @Autowired
    private CourtApplyBiz courtApplyBiz;

    @RequestMapping(value = "courtApply/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageId);
        modelMap.put("query",baseQuery);
        BizResult bizResult = courtApplyBiz.list(baseQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "courtApply/list.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "courtApply/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, CourtApplyEditForm courtApplyEditForm,
        BindingResult result, Map<String,Object> model) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = courtApplyBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            courtApplyEditForm.initForm(((CourtApplyDO)bizResult.data.get("courtApplyDO")));
            return "courtApply/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "courtApply/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = courtApplyBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "courtApply/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "courtApply/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, CourtApplyForm courtApplyForm,
        BindingResult result, Map<String,Object> model) {
        return "courtApply/create.vm";
    }

    @RequestMapping(value = "courtApply/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid  CourtApplyForm courtApplyForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "courtApply/create.vm";
        }
        CourtApplyDO courtApplyDO = courtApplyForm.toDO();
        BizResult bizResult = courtApplyBiz.create(courtApplyDO);
        if (bizResult.success) {
            return "redirect:/courtApply/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "courtApply/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid CourtApplyEditForm courtApplyEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "courtApply/edit.vm";
        }
        CourtApplyDO courtApplyDO = courtApplyEditForm.toDO();
        BizResult bizResult = courtApplyBiz.update(courtApplyDO);
        if (bizResult.success) {
            return "redirect:/courtApply/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "courtApply/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = courtApplyBiz.delete(id);
        if (bizResult.success) {
            return "courtApply/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
