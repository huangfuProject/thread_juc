package com.five;

/**
 * @author huangfukexing
 * @date 2024/4/8 14:58
 */
public class GcThreadLocal {
    private final ThreadLocal<TestUser> T = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        GcThreadLocal gcThreadLocal = new GcThreadLocal();
        gcThreadLocal.a();
        //gcThreadLocal = null;
        //System.gc();
        Thread.sleep(2000);
        gcThreadLocal = new GcThreadLocal();
        System.out.println("adsadasdasdasdasdasd");
        System.out.println(gcThreadLocal.T.get());
    }

    public void a() throws InterruptedException {
        b();
        Thread.sleep(2000);
        System.gc();
        System.out.println(T.get());
    }

    public void b() throws InterruptedException {
        c();
        Thread.sleep(2000);
        System.gc();
    }

    public void c() throws InterruptedException {
        d();
        Thread.sleep(2000);
        System.gc();
    }

    public void d() throws InterruptedException {
        TestUser testUser = new TestUser();
        testUser.setName("huangfu");
        T.set(testUser);

        testUser = null;
        System.gc();
        Thread.sleep(2000);
        System.gc();

    }


}
