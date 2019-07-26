class test{
    public static void main(String[]args){

    }
    /**
     * 消费者生产者
     */
    static class myThread extends Thread{
        public void run(){

        }
    }

    static class myRunnable implements Runnable{
        public void run(){
            
        }
    }

    static class mycallable<T> implements Callable<T>{
        public T call(){
            System.out.print("================================");
            return (T)new User("XXXXXXXXX");
        }
    }
}