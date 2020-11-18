package com.celano.config;

public enum PracticeName {
    CO_BAN_1(0),
    CO_BAN_2(1),
    CHAO_HOI(2),
    MON_AN(3),
    DONG_VAT(4),
    SO_NHIEU(5),
    SO_HUU(6),
    D_T_K_QUAN(7),
    QUAN_AO(8),
    DONG_TU(9),
    MAU_SAC(10),
    CAU_HOI(11),
    LIEN_TU(12),
    GIOI_TU(13),
    THOI_GIAN(14),
    GIA_DINH(15),
    CONG_VIEC(16),
    TINH_TU_1(17),
    D_TU_H_T_2(18),
    PHO_TU(19),
    NOI_CHON(20),
    TAN_NGU(21),
    CON_NGUOI(22),
    DU_LICH(23),
    HAN_DINH(24),
    SO(25),
    D_TU_H_T_3(26),
    GIAO_DUC(27);

    int number;

    PracticeName(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }
}
