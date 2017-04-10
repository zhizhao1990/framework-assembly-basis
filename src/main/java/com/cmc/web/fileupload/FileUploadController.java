package com.cmc.web.fileupload;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cmc.common.constants.AjaxGeneralResult;
import com.cmc.common.constants.IsSuccess;
import com.cmc.common.utils.FileUploadUtil;

@Controller
@RequestMapping("upload.htm")
public class FileUploadController {

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, params = "action=upload")
    public AjaxGeneralResult upload(MultipartHttpServletRequest req) {
        // String rst = FileUploadUtil.upload(request);
        String rst = FileUploadUtil.uploadViaSpring(req.getFile("avatar"));
        if (StringUtils.isNotBlank(rst)) {
            JSONObject data = new JSONObject();
            data.put("url", rst);
            return AjaxGeneralResult.getGeneralRst(IsSuccess.YES.getCode(), IsSuccess.YES.getDesc(), data);
        }
        return AjaxGeneralResult.getFailureRst("a");
    }

}