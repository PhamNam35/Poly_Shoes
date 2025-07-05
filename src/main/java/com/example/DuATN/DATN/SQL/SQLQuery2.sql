CREATE DATABASE shoe_store1;
GO

USE shoe_store1;
GO

-- Người dùng
CREATE TABLE Nguoi_Dung (
    nguoi_dung_id INT IDENTITY(1,1) PRIMARY KEY,
    ho_ten NVARCHAR(100),
    ngay_sinh DATE,
    ten_dang_nhap NVARCHAR(50),
    mat_khau NVARCHAR(100),
    email NVARCHAR(100),
    so_dien_thoai NVARCHAR(20),
    ngay_tao DATE
);

-- Voucher
CREATE TABLE Voucher (
    voucher_id INT IDENTITY(1,1) PRIMARY KEY,
    so_luong_con_lai INT,
    loai NVARCHAR(50),
    trang_thai BIT,
    han_su_dung DATE
);

-- Địa chỉ giao hàng
CREATE TABLE Dia_Chi_Giao_Hang (
    dia_chi_giao_hang_id INT IDENTITY(1,1) PRIMARY KEY,
    ten_nguoi_nhan NVARCHAR(100),
    so_dien_thoai NVARCHAR(20),
    dia_chi_cu_the NVARCHAR(255),
    ghi_chu NVARCHAR(255),
    mac_dinh BIT
);

-- Khách hàng
CREATE TABLE Khach_Hang (
    khach_hang_id INT IDENTITY(1,1) PRIMARY KEY,
    ten_dang_nhap NVARCHAR(50),
    mat_khau NVARCHAR(100),
    so_dien_thoai NVARCHAR(20),
    ngay_dang_ky DATE,
    ho_ten NVARCHAR(100),
    dia_chi_giao_hang_id INT,
    FOREIGN KEY (dia_chi_giao_hang_id) REFERENCES Dia_Chi_Giao_Hang(dia_chi_giao_hang_id)
);

-- Đơn hàng (đã sửa IDENTITY)
CREATE TABLE Don_Hang (
    don_hang_id INT IDENTITY(1,1) PRIMARY KEY,
    voucher_id INT,
    tong_tien DECIMAL(18, 2),
    ngay_dat_hang DATE,
    trang_thai NVARCHAR(50),
    dia_chi_giao_hang NVARCHAR(255),
    khach_hang_id INT,
    trang_thai_thanh_toan NVARCHAR(50),
    nguoi_dung_id INT,
    FOREIGN KEY (voucher_id) REFERENCES Voucher(voucher_id),
    FOREIGN KEY (khach_hang_id) REFERENCES Khach_Hang(khach_hang_id),
    FOREIGN KEY (nguoi_dung_id) REFERENCES Nguoi_Dung(nguoi_dung_id)
);

-- Chi tiết đơn hàng
CREATE TABLE Chi_Tiet_Don_Hang (
    chi_tiet_don_hang_id INT IDENTITY(1,1) PRIMARY KEY,
    don_hang_id INT,
    so_luong INT,
    chi_tiet_san_pham INT,
    FOREIGN KEY (don_hang_id) REFERENCES Don_Hang(don_hang_id)
);

-- Thương hiệu
CREATE TABLE Thuong_Hieu (
    thuong_hieu_id INT IDENTITY(1,1) PRIMARY KEY,
    ten_thuong_hieu NVARCHAR(100)
);

-- Xuất xứ
CREATE TABLE Xuat_Xu (
    xuat_xu_id INT IDENTITY(1,1) PRIMARY KEY,
    ten_dat_nuoc_san_xuat NVARCHAR(100)
);

-- Kiểu giày
CREATE TABLE Kieu_Giay_Giay (
    kieu_giay_giay_id INT IDENTITY(1,1) PRIMARY KEY,
    ten_kieu_giay_giay NVARCHAR(100)
);

-- Sản phẩm
CREATE TABLE San_Pham (
    san_pham_id INT IDENTITY(1,1) PRIMARY KEY,
    ten_san_pham NVARCHAR(100),
    trang_thai BIT,
    chat_lieu NVARCHAR(100),
    mo_ta NVARCHAR(255),
    ngay_nhap DATE,
    thuong_hieu_id INT,
    kieu_giay_giay_id INT,
    xuat_xu_id INT,
    FOREIGN KEY (thuong_hieu_id) REFERENCES Thuong_Hieu(thuong_hieu_id),
    FOREIGN KEY (kieu_giay_giay_id) REFERENCES Kieu_Giay_Giay(kieu_giay_giay_id),
    FOREIGN KEY (xuat_xu_id) REFERENCES Xuat_Xu(xuat_xu_id)
);

-- Kích thước
CREATE TABLE Kich_Thuoc (
    kich_thuoc_id INT IDENTITY(1,1) PRIMARY KEY,
    size NVARCHAR(10)
);

-- Màu sắc
CREATE TABLE Mau_Sac (
    mau_sac_id INT IDENTITY(1,1) PRIMARY KEY,
    ten_mau NVARCHAR(50),
    ma_mau_hex CHAR(7)
);

-- Hình ảnh sản phẩm
CREATE TABLE Hinh_Anh_San_Pham (
    hinh_anh_san_pham_id INT IDENTITY(1,1) PRIMARY KEY,
    duong_dan_anh NVARCHAR(255),
    anh_chinh BIT
);

-- Chi tiết sản phẩm
CREATE TABLE Chi_Tiet_San_Pham (
    chi_tiet_sp_id INT IDENTITY(1,1) PRIMARY KEY,
    san_pham_id INT,
    kich_thuoc_id INT,
    mau_sac_id INT,
    so_luong_ton INT,
    trang_thai BIT,
    ngay_cap_nhap DATE,
    gia_ban DECIMAL(18,2),
    hinh_anh_san_sp_id INT,
    FOREIGN KEY (san_pham_id) REFERENCES San_Pham(san_pham_id),
    FOREIGN KEY (kich_thuoc_id) REFERENCES Kich_Thuoc(kich_thuoc_id),
    FOREIGN KEY (mau_sac_id) REFERENCES Mau_Sac(mau_sac_id),
    FOREIGN KEY (hinh_anh_san_sp_id) REFERENCES Hinh_Anh_San_Pham(hinh_anh_san_pham_id)
);

-- Ràng buộc bổ sung: Chi tiết đơn hàng -> Chi tiết sản phẩm
ALTER TABLE Chi_Tiet_Don_Hang
ADD CONSTRAINT fk_ctdh_ctsp
FOREIGN KEY (chi_tiet_san_pham) REFERENCES Chi_Tiet_San_Pham(chi_tiet_sp_id);

-- Chi tiết giỏ hàng
CREATE TABLE Chi_Tiet_Gio_Hang (
    chi_tiet_gio_hang_id INT IDENTITY(1,1) PRIMARY KEY,
    khach_hang_id INT,
    san_pham_id INT,
    phan_loai NVARCHAR(100),
    trang_thai BIT,
    FOREIGN KEY (khach_hang_id) REFERENCES Khach_Hang(khach_hang_id),
    FOREIGN KEY (san_pham_id) REFERENCES San_Pham(san_pham_id)
);

-- Chèn dữ liệu mẫu
INSERT INTO Nguoi_Dung (ho_ten, ngay_sinh, ten_dang_nhap, mat_khau, email, so_dien_thoai, ngay_tao) VALUES
(N'Nguyễn Văn A', '1990-01-01', 'nguyenvana', 'matkhau1', 'a@gmail.com', '0909123456', '2025-01-01'),
(N'Trần Thị B', '1995-02-02', 'tranthib', 'matkhau2', 'b@gmail.com', '0909987654', '2025-02-01');

INSERT INTO Voucher (so_luong_con_lai, loai, trang_thai, han_su_dung) VALUES
(100, N'Giảm 10%', 1, '2025-12-31'),
(50, N'Freeship', 1, '2025-06-30');

INSERT INTO Dia_Chi_Giao_Hang (ten_nguoi_nhan, so_dien_thoai, dia_chi_cu_the, ghi_chu, mac_dinh) VALUES
(N'Nguyễn Văn A', '0909123456', N'123 Đường A, Q.1, TP.HCM', N'Giao buổi sáng', 1),
(N'Trần Thị B', '0909987654', N'456 Đường B, Q.2, TP.HCM', N'Giao buổi chiều', 0);

INSERT INTO Khach_Hang (ten_dang_nhap, mat_khau, so_dien_thoai, ngay_dang_ky, ho_ten, dia_chi_giao_hang_id) VALUES
('khachhang1', 'mk123', '0911223344', '2025-03-01', N'Lê Văn C', 1),
('khachhang2', 'mk456', '0933445566', '2025-04-01', N'Phạm Thị D', 2);

INSERT INTO Thuong_Hieu (ten_thuong_hieu) VALUES
(N'Nike'), (N'Adidas');

INSERT INTO Xuat_Xu (ten_dat_nuoc_san_xuat) VALUES
(N'Mỹ'), (N'Việt Nam');

INSERT INTO Kieu_Giay_Giay (ten_kieu_giay_giay) VALUES
(N'Sneaker'), (N'Sandal');

INSERT INTO San_Pham (ten_san_pham, trang_thai, chat_lieu, mo_ta, ngay_nhap, thuong_hieu_id, kieu_giay_giay_id, xuat_xu_id) VALUES
(N'Giày Nike Air', 1, N'Da tổng hợp', N'Mẫu giày hot 2025', '2025-04-01', 1, 1, 1),
(N'Giày Adidas Runner', 1, N'Vải', N'Giày chạy bộ chuyên dụng', '2025-04-05', 2, 1, 2);

INSERT INTO Kich_Thuoc (size) VALUES ('39'), ('40'), ('41');

INSERT INTO Mau_Sac (ten_mau, ma_mau_hex) VALUES (N'Đen', '#000000'), (N'Trắng', '#FFFFFF');

INSERT INTO Hinh_Anh_San_Pham (duong_dan_anh, anh_chinh) VALUES
('images/nike_air.jpg', 1),
('images/adidas_runner.jpg', 1);

INSERT INTO Chi_Tiet_San_Pham (san_pham_id, kich_thuoc_id, mau_sac_id, so_luong_ton, trang_thai, ngay_cap_nhap, gia_ban, hinh_anh_san_sp_id) VALUES
(1, 1, 1, 10, 1, '2025-05-01', 750000, 1),
(2, 2, 2, 8, 1, '2025-05-01', 1000000, 2);

INSERT INTO Don_Hang (voucher_id, tong_tien, ngay_dat_hang, trang_thai, dia_chi_giao_hang, khach_hang_id, trang_thai_thanh_toan, nguoi_dung_id) VALUES
(1, 1500000, '2025-05-01', N'Đang xử lý', N'123 Đường A, Q.1', 1, N'Chưa thanh toán', 1),
(2, 2000000, '2025-05-05', N'Đã giao', N'456 Đường B, Q.2', 2, N'Đã thanh toán', 2);

INSERT INTO Chi_Tiet_Don_Hang (don_hang_id, so_luong, chi_tiet_san_pham) VALUES
(1, 2, 1),
(2, 1, 2);

INSERT INTO Chi_Tiet_Gio_Hang (khach_hang_id, san_pham_id, phan_loai, trang_thai) VALUES
(1, 1, N'Mẫu đặc biệt', 1),
(2, 2, N'Phiên bản giới hạn', 1);
-- XÓA cột nếu đã tồn tại sai định nghĩa
ALTER TABLE Chi_Tiet_Don_Hang DROP COLUMN chi_tiet_don_hang_id;

-- TẠO lại cột đúng kiểu IDENTITY (tự động tăng)
ALTER TABLE Chi_Tiet_Don_Hang
ADD chi_tiet_don_hang_id INT IDENTITY(1,1) PRIMARY KEY;
ALTER TABLE Hinh_Anh_San_Pham
ADD chi_tiet_san_pham_id INT;
UPDATE Hinh_Anh_San_Pham
SET chi_tiet_san_pham_id = 1 WHERE hinh_anh_san_pham_id = 1;
select * from Hinh_Anh_San_Pham
UPDATE Hinh_Anh_San_Pham
SET chi_tiet_san_pham_id = 2 WHERE hinh_anh_san_pham_id = 2;SELECT hasp.hinh_anh_san_pham_id, ctsp.san_pham_id, hasp.duong_dan_anh, hasp.anh_chinh
FROM Hinh_Anh_San_Pham hasp
JOIN Chi_Tiet_San_Pham ctsp ON hasp.hinh_anh_san_pham_id  = ctsp.chi_tiet_sp_id;
UPDATE Hinh_Anh_San_Pham
SET duong_dan_anh = 'img/default.jpg'
WHERE hinh_anh_san_pham_id = 1;
UPDATE Hinh_Anh_San_Pham
SET duong_dan_anh = 'img/anh2.jpg'
WHERE hinh_anh_san_pham_id = 2;

ALTER TABLE khach_hang ADD CONSTRAINT uk_khach_ten_dang_nhap UNIQUE (ten_dang_nhap);
ALTER TABLE nguoi_dung ADD CONSTRAINT uk_user_ten_dang_nhap UNIQUE (ten_dang_nhap);
