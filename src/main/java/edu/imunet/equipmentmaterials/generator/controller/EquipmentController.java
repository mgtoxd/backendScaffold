package edu.imunet.equipmentmaterials.generator.controller;

import java.util.HashMap;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;;
import io.swagger.v3.oas.annotations.tags.Tag;
import edu.imunet.equipmentmaterials.vo.ResultVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import edu.imunet.equipmentmaterials.generator.service.IEquipmentService;
import edu.imunet.equipmentmaterials.generator.entity.Equipment;
import edu.imunet.equipmentmaterials.controller.BaseController;

/**
 * equipmentController
 *
 * @author mataoxun
 * @date 2023-06-08
 */
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/equipment")
@Tag(name = "equipmentCRUD")
public class EquipmentController extends BaseController {
private final IEquipmentService equipmentService;

        /**
         * 分页查询equipment列表
         */
        @PostMapping("/pageList/{page}/{size}")
        @Operation(summary ="分页查询equipment列表")
        public ResultVo list(@RequestBody HashMap<String, Object> bo,@PathVariable("page") int page,@PathVariable("size") int size){
                Page<Equipment> pageCfg=new Page<>(page,size);
                QueryWrapper<Equipment> queryWrapper=new QueryWrapper<>();
                queryWrapper.allEq(bo);
                Page<Equipment> list=equipmentService.page(pageCfg,queryWrapper);
                return makeSuccessResult(list);
        }

        /**
         * 根据id获取equipment信息
         */
        @GetMapping("/getById")
        @Operation(summary ="根据id获取equipment详细信息")
        public ResultVo getById(@RequestParam("id") Long id){
                return makeSuccessResult(equipmentService.getById(id));
        }

        /**
         * 根据id新增equipment
         */
        @PostMapping("/addById")
        @Operation(summary ="根据id新增equipment")
        public ResultVo addById(@RequestBody Equipment bo){
                return makeSuccessResult(equipmentService.save(bo));
        }


        /**
         * 根据id修改equipment
         */
        @PostMapping("/updateById")
        @Operation(summary ="根据id修改equipment")
        public ResultVo edit(@RequestBody Equipment bo){
                return makeSuccessResult(equipmentService.updateById(bo));
        }

        /**
         * 根据id新增或修改equipment
         */
        @PostMapping("/addOrUpdateById")
        @Operation(summary ="根据id新增或修改equipment")
        public ResultVo addOrUpdateById(@RequestBody Equipment bo){
                return makeSuccessResult(equipmentService.saveOrUpdate(bo));
        }
        /**
         * 根据id删除equipment
         */
        @GetMapping("/deleteById")
        @Operation(summary ="根据id删除equipment")
        public ResultVo remove(@RequestParam("id") Long id){
                return makeSuccessResult(equipmentService.removeById(id));
        }
}