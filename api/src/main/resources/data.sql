SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO category(name, description, created_at)
VALUES ('Sữa tăng cân', 'Sữa tăng cân cho bé', NOW()),
       ('Sữa tăng chiều cao', 'Sữa tăng chiều cao cho bé', NOW()),
       ('Sữa đặc trị', 'Sữa cho nguười cao tuổi và cho người tiểu đường', NOW()),
       ('Sữa non', 'Sữa cho trẻ sơ sinh', NOW());

INSERT INTO brand(name, description, created_at)
VALUES ('Ensure', 'Sữa Ensure', NOW()),
       ('Nestle', 'Sữa Nestle', NOW()),
       ('Nutifood', 'Sữa Nutifood', NOW()),
       ('Vinamilk', 'Sữa Vinamilk', NOW()),
       ('Truemom', 'Sữa Truemom', NOW());

INSERT INTO product(name, description, price, stock, batch_number, expiry_date, created_at, category_id, brand_id)
VALUES
    ('Sữa bột Nan Nga số 3 800g (Trên 1 tuổi)', 'Sữa Nan Nga số 3 là sản phẩm sữa của công ty Nestle nhập khẩu từ Nga cung cấp những dinh dưỡng cần thiết cho trẻ trong giai đoạn từ 1 tuổi trở lên. Sữa cung cấp đạm chất lượng Optipro với hàm lượng cần thiết cho sự phát triển khỏe mạnh lâu dài mà không gây quá tải cho các cơ quan còn non nớt của trẻ. Hệ lợi khuẩn Bifidus BL bổ sung các vi sinh vật có lợi Probiotics giúp hệ tiêu hóa của bé hoạt động tốt hơn, tăng cường hệ miễn dịch đường ruột cho trẻ để trẻ hấp thu các chất dinh dưỡng một cách tự nhiên và phòng chống các tác nhân gây bệnh hiệu quả. Đặc biệt sữa bổ sung DHA – axit béo hỗ trợ tốt cho sự phát triển trí não và thị lực của bé trong giai đoạn tăng cường khám phá, học hỏi từ thế giới xung quanh.',
        489000, 100, '123456', '2024-12-31', NOW(), 1, 2),
    ('Sữa bột Vinamilk Optimum Gold số 4, 850g (cho bé 2-6 tuổi)', 'Sữa bột Vinamilk Optimum số 4 với công thức dễ tiêu hóa, là nền tảng cho việc hấp thu các dưỡng chất thiết yếu cho trẻ từ 2-6 tuổi, giúp tăng cường sức đề kháng, phát triển não bộ và thể chất.',
     389000, 100, '123457', '2024-12-31', NOW(), 2, 4),
    ('Sữa Non ILDONG số 1 Hàn Quốc 90 gói/90g (trẻ 0-12 tháng)', 'Sữa Non ILDong số 1 giúp tăng cường sức đề kháng tự nhiên cho bé từ 0-12 tháng được các mẹ đánh giá tốt nhất hiện nay.',
     269000, 100, '123458', '2024-12-31', NOW(), 4, 5);