package fr.deroffal.eshop.observability.traces;

public class Example {

    private String world;

    public String hello() {
        return "hello";
    }

    public void world(String world) {
        this.world = world;
    }

    public String getWorld() {
        return world;
    }

    public void throwException() {
        throw new RuntimeException("this is an exception !");
    }
}
