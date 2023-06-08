package edu.imunet.equipmentmaterials.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.imunet.equipmentmaterials.util.JsonUtil;
import edu.imunet.equipmentmaterials.vo.ResultVo;

public class BaseController {
    public ResultVo makeSuccessResult(Object obj) {
        return new ResultVo(200, "success", obj);
    }

    public ResultVo makeFailResult(String msg) {
        return new ResultVo(500, msg, null);
    }

}
