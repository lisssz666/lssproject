--- 验证码
drop table if exists red_captcha;
create table red_captcha (
  captcha_id           bigint(20)      not null auto_increment    comment '验证码id',
  captcha_key          varchar(50)     default ''                 comment '验证码Key',
  captcha_code         varchar(50)     default ''                 comment '验证码',
  captcha_expiration   int(4)          default 2                  comment '验证码有效期',
  captcha_util         varchar(20)     default null               comment '有效期单位',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  expreation_time   datetime                                   comment '超期时间',
  primary key (captcha_id)
) engine=innodb auto_increment=200 comment = '验证码表';

drop table if exists red_user;
create table red_user (
  user_id           bigint(20)        not null auto_increment    comment '缓存ID',
  reduser_id        varchar(200)        default ''               comment '用户ID',
  user_key          varchar(50)       default ''                 comment '缓存KEY',
  sysuser           blob      default ''                 comment '缓存用户信息',
  logininfo         varchar(250)      default ''                 comment '缓存登录信息',
  permissions       varchar(250)      default ''                 comment '权限信息',  
  captcha_expiration   int(4)          default 2                  comment '有效期',
  captcha_util         varchar(20)     default null               comment '有效期单位',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  expreation_time   datetime                                   comment '超期时间',
  primary key (user_id)
) engine=innodb auto_increment=200 comment = '登录用户表';
