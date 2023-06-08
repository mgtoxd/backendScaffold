package edu.imunet.equipmentmaterials;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import edu.imunet.equipmentmaterials.controller.BaseController;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Types;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Generate {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:postgresql://localhost:26257/equipment?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC", "root", "")
                .globalConfig(builder -> {
                    builder.author("mataoxun") // 设置作者
                            .outputDir("/Users/mataoxun/code/group/equipmentMaterials/src/main/java")
                            .fileOverride(); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("edu.imunet.equipmentmaterials") // 设置父包名
                            .moduleName("generator") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "/Users/mataoxun/code/group/equipmentMaterials/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("equipment")// 设置需要生成的表名
                            .addTablePrefix("t_", "c_")
                            .entityBuilder()
                            .enableLombok()
                            .enableChainModel()
                            .addTableFills(Arrays.asList(new Column("create_time", FieldFill.INSERT),new Column("update_time", FieldFill.INSERT_UPDATE)))
                            .idType(IdType.ASSIGN_ID)
                            .mapperBuilder()
                            .mapperAnnotation(Mapper.class)
                            .controllerBuilder()
                            .superClass(BaseController.class)// 设置填充字段
                            ; // 设置过滤表前缀
                })
//                .templateEngine(new FreemarkerTemplateEngine())
                .templateConfig(builder -> {
                   builder.controller("/templates/controller.java");
                })
                .execute();
    }
}
