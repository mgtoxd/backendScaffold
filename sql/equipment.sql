create table equipment
(
    id                                  int8                 not null
        constraint "primary"
            primary key,
    equipment_name                      text                 not null,
    equipment_category                  text                 not null,
    equipment_introduction              text                 not null,
    equipment_the_current_use_situation int8 default 0:::INT not null,
    create_time                         timestamptz(6)       not null,
    update_time                         timestamptz(6)       not null
);

INSERT INTO public.equipment (id, equipment_name, equipment_category, equipment_introduction, equipment_the_current_use_situation, create_time, update_time) VALUES (1666632402040008706, '测试', '2312312', '测试', 0, '2023-06-07 18:24:29.577000 +00:00', '2023-06-07 18:24:29.577000 +00:00');
