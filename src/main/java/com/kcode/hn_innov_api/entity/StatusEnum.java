package com.kcode.hn_innov_api.entity;

public enum StatusEnum {
    EN_ATTENTE {
        @Override
        public StatusEnum next() { return EN_PREPARATION; }
    },
    EN_PREPARATION {
        @Override
        public StatusEnum next() { return EN_COURS; }
    },
    EN_COURS  {
        @Override
        public StatusEnum next() { return TERMINE; }
    },
    TERMINE  {
        @Override
        public StatusEnum next() { return EN_ATTENTE; }
    };


    public abstract StatusEnum next();
}
