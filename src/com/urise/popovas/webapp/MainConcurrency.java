package com.urise.popovas.webapp;

public class MainConcurrency {
    static class Account {
        private double accSumm = 10000.;

        synchronized void sendMoney(double summ, Account acc) {
            accSumm = accSumm - summ;
            acc.receiveMoney(summ);
        }

        synchronized void receiveMoney(double summ) {
            accSumm = accSumm + summ;
        }
    }

    public static void main(String[] args) {
        final Account acc1 = new Account();
        final Account acc2 = new Account();

        for (int i = 0; i< 10000; i++){
            new Thread(() -> {
                System.out.println("First thread start");
                acc1.sendMoney(100., acc2);
                System.out.println("First thread end");
            }).start();
            new Thread(() -> {
                System.out.println("Second thread start");
                acc2.sendMoney(120., acc1);
                System.out.println("Second thread end");
            }).start();
        }

    }


}
