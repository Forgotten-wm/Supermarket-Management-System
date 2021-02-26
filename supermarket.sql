##table user
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`       int(11) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `username` varchar(50) NOT NULL UNIQUE KEY COMMENT '用户账号',
    `password` varchar(50) NOT NULL COMMENT '用户密码'
) COMMENT '用户表';

##table supplier
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`
(
    `id`   int(11) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `name` varchar(50)  NOT NULL UNIQUE KEY COMMENT '供货商名称',
    `loc`  varchar(200) NOT NULL COMMENT '供货商地址'
) COMMENT '供货商表';

##table goods
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`
(
    `id`              int(11) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `name`            varchar(50)   NOT NULL UNIQUE KEY COMMENT '商品名称',
    `type`            varchar(50)   NOT NULL COMMENT '商品类别',
    `price`           decimal(9, 2) NOT NULL COMMENT '商品价格',
    `unit`            varchar(5)    NOT NULL COMMENT '商品单位',
    `production_date` date          NOT NULL COMMENT '生产日期',
    `expiry_date`     date          NOT NULL COMMENT '过期日期',
    `supplier_id`     int(11)       NOT NULL COMMENT '供货商',
    `information`     varchar(500)  NULL COMMENT '描述信息',
    CONSTRAINT fk_goods_supplier_id FOREIGN KEY (`supplier_id`) REFERENCES supplier (id)
) COMMENT '商品表';

##table picture
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture`
(
    `id`          int(11) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `src`         varchar(200) NOT NULL COMMENT '图片路径',
    `title`       varchar(50)  NULL COMMENT '图片标题',
    `information` varchar(500) NULL COMMENT '描述信息'
) COMMENT '商品图片表';

##table goods_picture
DROP TABLE IF EXISTS `goods_picture`;
CREATE TABLE `goods_picture`
(
    `id`         int(11) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `goods_id`   int(11) NOT NULL COMMENT '商品',
    `picture_id` int(11) NOT NULL COMMENT '图片',
    CONSTRAINT fk_goods_picture_goods_id FOREIGN KEY (`goods_id`) REFERENCES goods (id),
    CONSTRAINT fk_goods_picture_picture_id FOREIGN KEY (`picture_id`) REFERENCES picture (id)
) COMMENT '商品图片中间表';

##table goods_count
DROP TABLE IF EXISTS `goods_count`;
CREATE TABLE `goods_count`
(
    `id`         int(11) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `goods_id`   int(11) NOT NULL COMMENT '商品',
    `count`  int(11) NOT NULL COMMENT '数量',
    CONSTRAINT fk_goods_count_goods_id FOREIGN KEY (`goods_id`) REFERENCES goods (id)
) COMMENT '商品数量表';




