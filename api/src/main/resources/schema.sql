CREATE TABLE `user`
(
    `id`           integer PRIMARY KEY AUTO_INCREMENT,
    `username`     varchar(255),
    `password`     varchar(255),
    `email`        varchar(255),
    `phone_number` varchar(255),
    `created_at`   timestamp
);

CREATE TABLE `role`
(
    `id`         integer PRIMARY KEY AUTO_INCREMENT,
    `name`       varchar(255),
    `created_at` timestamp
);

CREATE TABLE `user_role`
(
    `user_id` integer,
    `role_id` integer,
    PRIMARY KEY (`user_id`, `role_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
);

CREATE TABLE `category`
(
    `id`         integer PRIMARY KEY AUTO_INCREMENT,
    `name`       varchar(255) NOT NULL,
    `created_at` timestamp
);

CREATE TABLE `brand`
(
    `id`         integer PRIMARY KEY AUTO_INCREMENT,
    `name`       varchar(255) NOT NULL,
    `created_at` timestamp
);

CREATE TABLE `product`
(
    `id`           integer PRIMARY KEY AUTO_INCREMENT,
    `name`         varchar(255) NOT NULL,
    `description`  varchar(255),
    `price`        double       NOT NULL,
    `stock`        integer      NOT NULL,
    `batch_number` varchar(255) NOT NULL,
    `expiry_date`  timestamp    NOT NULL,
    `created_at`   timestamp,
    `category_id`  integer,
    `brand_id`     integer,
    FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
    FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
);

CREATE TABLE `order`
(
    `id`          integer PRIMARY KEY AUTO_INCREMENT,
    `total_price` double    NOT NULL,
    `order_date`  timestamp NOT NULL,
    `status`      varchar(255),
    `created_at`  timestamp,
    `user_id`     integer,
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

CREATE TABLE `order_detail`
(
    `id`         integer PRIMARY KEY AUTO_INCREMENT,
    `price`      double NOT NULL,
    `quantity`   int    NOT NULL,
    `created_at` timestamp,
    `order_id`   integer,
    `product_id` integer,
    FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
    FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
);

CREATE TABLE `refund`
(
    `id`          integer PRIMARY KEY AUTO_INCREMENT,
    `total_price` double       NOT NULL,
    `refund_date` timestamp    NOT NULL,
    `status`      varchar(255) NOT NULL,
    `reason`      varchar(255),
    `created_at`  timestamp,
    `order_id`    integer,
    FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
);

CREATE TABLE `promotion`
(
    `id`                  integer PRIMARY KEY AUTO_INCREMENT,
    `code`                varchar(255) NOT NULL,
    `discount_percentage` integer      NOT NULL,
    `start_date`          timestamp    NOT NULL,
    `end_date`            timestamp    NOT NULL,
    `created_at`          timestamp
);

CREATE TABLE `sale`
(
    `id`            integer PRIMARY KEY AUTO_INCREMENT,
    `quantity_sold` integer   NOT NULL,
    `sale_date`     timestamp NOT NULL,
    `created_at`    timestamp,
    `product_id`    integer,
    FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
);
