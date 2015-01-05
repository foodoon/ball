package guda.ball.util;

import guda.tools.web.page.BizResult;
import org.springframework.util.StringUtils;

/**
 * Created by foodoon on 2014/8/1.
 */
public class BizResultHelper {

    public static void setResultCode(BizResult bizResult, String code) {
        if (!StringUtils.hasText(code)) {
            return;
        }
        bizResult.code = code;
        bizResult.msg = ErrorCode.getMessage(code);
    }

    public static BizResult newCommonError() {
        BizResult bizResult  = new BizResult();
        bizResult.code = CommonResultCode.UNKOWN_ERROR;
        bizResult.msg = ErrorCode.getMessage(CommonResultCode.UNKOWN_ERROR);
        return bizResult;
    }

    public static BizResult newSuccess() {
        BizResult bizResult  = new BizResult();
        bizResult.success = true;
        bizResult.code = CommonResultCode.SUCCESS;
        bizResult.msg = ErrorCode.getMessage(CommonResultCode.SUCCESS);
        return bizResult;
    }

    public static BizResult newResultCode(String code) {
        BizResult bizResult  = new BizResult();
        bizResult.code = code;
        bizResult.msg = ErrorCode.getMessage(code);
        return bizResult;
    }
}
