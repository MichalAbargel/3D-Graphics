package unittests;

import elements.*;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;


class MiniProjectImage {
    private Scene scene1 = new Scene("Test scene").setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
    private Camera camera1 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setViewPlaneSize(400, 200) //
            .setDistance(1000);

    private  Geometry sphere = new Sphere(15, new Point3D(57, 50, 0)) //
            .setEmission(new Color(java.awt.Color.CYAN)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(100));

    private  Geometry star1 = new Sphere(15, new Point3D(-80, 20, -50)) //
            .setEmission(new Color(java.awt.Color.BLUE)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(50));

    private  Geometry star2 = new Sphere(10, new Point3D(-50, 10, 50)) //
            .setEmission(new Color(139,0,0)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(100));

    private  Geometry triangle1 = new Triangle( //
            new Point3D(-150, -150, -150), new Point3D(150, -150, -150), new Point3D(75, 75, -150));
    private  Geometry triangle = new Triangle( //
            new Point3D(-150, -150, -150), new Point3D(-70, 70, -50), new Point3D(75, 75, -150)).
            setMaterial(new Material().setKd(0.8).setKs(0.2).setnShininess(300)).setEmission(new Color(0,0,248));



   // @Test
//    public void miniPTest(){
//
//        Material m = new Material().setKd(1).setKs(1).setnShininess(20).setkT(0).setkR(0);
//        Plane plane = new Plane(new Vector(0, -1, 0),new Point3D(0, -300, 1000));
//        plane.setMaterial(m).setEmission(new Color(0, 0, 248));
//
//        scene1.lights.add(new DirectionalLight(new Color(500, 300, 0), new Vector(1, 1, 1)));
//        //scene1.lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(0, 0, -1)));
//        scene1.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(1, -50, 50), new Vector(1, 1, -2)) //
//                .setKl(0.00001).setKq(0.00000001));
//
//
//
//        Triangle wall11 = new Triangle(new Point3D(-150, -150, -150),
//                new Point3D(-995, -300, 1495),
//                new Point3D(-995, 100, 1495));
//        Triangle wall12 = new Triangle(new Point3D(-500, -300, 1000),
//                new Point3D(-500, 100, 1000),
//                new Point3D(-995, 100, 1495));
//        wall11.setMaterial(new Material().setKd(1).setnShininess(20).setKs(1).setkR(0).setkT(0)).
//                setEmission(new Color(100,100,100));
//        wall12.setMaterial(new Material().setKd(1).setKs(1).setnShininess(20).setkR(0).setkT(0)).
//                setEmission(new Color(100,100,100));
//
//        Triangle wall21 = new Triangle( new Point3D(-500, -300, 1000),
//                new Point3D(-500, 100, 1000),
//                new Point3D(207, 100, 1707)
//        );
//        Triangle wall22 = new Triangle(new Point3D(207, 100, 1707),
//                new Point3D(-500, -300, 1000),
//                new Point3D(207, -300, 1707));
//
//        wall21.setMaterial(new Material().setKd(1).setKs(1).setnShininess(20).setkT(0).setkR(0)).setEmission(new Color(100,100,100));
//        wall22.setMaterial(new Material().setKd(1).setKs(1).setnShininess(20).setkT(0).setkR(0)).setEmission(new Color(100,100,100));
//
//        Triangle roof41 = new Triangle( new Point3D(-767.5, 347.5, 1227.5),
//                new Point3D(-1065, 50, 1525),
//                new Point3D(-20.5, 347.5, 1974.5));
//        Triangle roof42 = new Triangle( new Point3D(-1065, 50, 1525),
//                new Point3D(-318, 50, 2272),
//                new Point3D(-20.5, 347.5, 1974.5));
//        roof41.setMaterial(new Material().setKd(1).setKs(1).setnShininess(20).setkT(0).setkR(0)).setEmission(new Color(190, 0, 0));
//        roof42.setMaterial(new Material().setKd(1).setKs(1).setnShininess(20).setkT(0).setkR(0)).setEmission(new Color(190, 0, 0));
//
//        //scene1._geometries.add(plane);
//        scene1._geometries.add(sphere);
//        scene1._geometries.add(star1);
//        scene1._geometries.add(star2);
//        scene1._geometries.add(roof41);
//        scene1._geometries.add(roof42);
//        //scene1._geometries.add(wall11);
//        //scene1._geometries.add(wall12);
//        //scene1._geometries.add(wall21);
//        //scene1._geometries.add(wall22);
//
//
//        scene1._geometries.add( //
//                new Triangle(new Point3D(-100, -100, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
//                        .setMaterial(new Material().setKs(0.8).setnShininess(60)));//
//        scene1._geometries.add(new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
//                .setMaterial(new Material().setKs(0.8).setnShininess(60))); //
//
//        ImageWriter imageWriter = new ImageWriter("MiniP2Final", 500, 500);
//        Render render = new Render()//
//                .setImageWriter(imageWriter) //
//                .setCamera(camera1) //
//                .setRayTracer(new RayTracerBasic(scene1));
//        render.renderImage();
//        render.writeToImage();
//
//
//
//
//    }

    @Test
    public void multipleLightSourceSphere() {
        Scene scene = new Scene("Test scene").setAmbientLight(new AmbientLight(Color.BLACK, 0.0)).setBackground(Color.BLACK);
        Camera camera=new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setDistance(700).setViewPlaneSize(150,150);

        Material m = new Material().setKd(1).setKs(1).setnShininess(20).setkT(0).setkR(0);
        Plane plane = new Plane(new Vector(0, -1, 0),new Point3D(0, -300, 1000));
        plane.setMaterial(m).setEmission(new Color(100, 100, 100));

        scene._geometries.add(sphere);
        scene._geometries.add(star1);
        scene._geometries.add( new Sphere(10, new Point3D(-50, -40, 80)) //
                .setEmission(new Color(139,0,0)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(100)));
        scene._geometries.add(
                new Sphere(50, new Point3D(0, 0, 50)).
                        setMaterial(new Material().setKd(0.7).setKs(0.8).setnShininess(100)).setEmission(new Color(java.awt.Color.DARK_GRAY)));

        scene._geometries.add(new Polygon(new Point3D(52, -50, 0),new Point3D(52, 35, 0),new Point3D(59, 35, 0),new Point3D(59, -50, 0)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)).setEmission(new Color(72,61,139)));

        scene._geometries.add(new Triangle(new Point3D(-70, -20, 100), new Point3D(-40, -50, 100), new Point3D(-68, -48, 96)) //
                .setEmission(new Color(java.awt.Color.BLUE)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30))); //
        scene._geometries.add(new Triangle(new Point3D(-40, -20, 100), new Point3D(-10, -50, 100), new Point3D(-28, -48, 96)) //
                .setEmission(new Color(java.awt.Color.YELLOW)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30))); //
        scene._geometries.add(new Triangle(new Point3D(-20, -20, 100), new Point3D(10, -50, 100), new Point3D(-8, -48, 96)) //
                .setEmission(new Color(java.awt.Color.YELLOW)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30))); //
        scene._geometries.add(new Triangle(new Point3D(0, -20, 100), new Point3D(30, -50, 100), new Point3D(12, -48, 96)) //
                .setEmission(new Color(220, 20, 60)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30))); //

        scene._geometries.add(new Polygon(new Point3D(100, -50, 0), new Point3D(100, -50, 100), new Point3D(-98, -50, 100),new Point3D(-60, -50, 0)) //
                .setEmission(new Color(java.awt.Color.CYAN)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)));
        scene._geometries.add(new Sphere(3, new Point3D(60, 80, -50)) //
                .setEmission(new Color(192,192,192)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(100)));
        scene._geometries.add(new Sphere(3, new Point3D(-60, 80, -50)) //
                .setEmission(new Color(192,192,192)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(100)));
        scene._geometries.add(new Sphere(2, new Point3D(0, 70, -50)) //
                .setEmission(new Color(192,192,192)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(100)));
        scene._geometries.add(new Sphere(2, new Point3D(-60, 30, -50)) //
                .setEmission(new Color(0,0,192)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(100)));
        scene._geometries.add(new Sphere(2, new Point3D(-80, -20, -50)) //
                .setEmission(new Color(0,0,192)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(100)));
        scene._geometries.add(new Sphere(5, new Point3D(-60, -20, -50)) //
                .setEmission(new Color(128,0,0)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(100)));

        for (int i = 0; i < 180; i++) {
            Sphere sphere5 = new Sphere(3,new Point3D((i * 2) * Math.pow(-1, (i % 2) + 1), Math.sin(i * Math.pow(-1, (i % 2) + 1)) * 50+4 , -100));
            sphere5.setMaterial(new Material().setKd(1).setKs(1).setnShininess(20).setkT(0).setkR(0));
            sphere5.setEmission(new Color((i / 2), 0, (250 - i)));
            scene._geometries.add(sphere5);
        }
//        scene.lights.add(new SpotLight(new Color(89, 213, 255), new Point3D(0, 80, 1000),
//                new Vector(0, -1, 0)).setKc(0.1).setKl(0.0000009).setKq(0.000006));
//        scene.lights.add(new PointLight(new Color(130, 13, 8), new Point3D(-500, 470, 990)).setKc(0).setKl(0.0005).setKq(0.000005));
//        scene.lights.add(new PointLight(new Color(0, 100, 8), new Point3D(1000, -50, 1350)).setKc(0).setKl(0.0005).setKq(0.000005));
//        scene.lights.add(new PointLight(new Color(14, 100, 82), new Point3D(1000, 0, 1350)).setKc(0).setKl(0.0005).setKq(0.000005));
//        scene.lights.add(new PointLight(new Color(60, 100, 9), new Point3D(-700, -50, 1560)).setKc(0).setKl(0.0005).setKq(0.000005));
//        scene.lights.add(new SpotLight(new Color(255, 151, 62), new Point3D(100, -460, 880),
//                new Vector(0, 1, 1)).setKc(0.1).setKl(0.01).setKq(0.0001));
//        scene.lights.add(new SpotLight(new Color(18, 98, 255), new Point3D(120, 470, 880),
//                new Vector(0, 0, 1)).setKl(0.01).setKq(0.0001));




        scene.lights.add(new DirectionalLight(new Color(0, 13, 255), new Vector(-20, -10, 20)));
        scene.lights.add(new PointLight(new Color(255, 0, 238), new Point3D(1200, 130, -100)).setKc(1).setKl(0.00001).setKq(0.000001));
        scene.lights.add(new SpotLight(new Color(17, 255, 0), new Point3D(20, 150, 20), new Vector(1, -1, 2)).setKc(1).setKl(0.00001).setKq(0.00000001));
        scene.lights.add(new DirectionalLight(new Color(500, 300, 0), new Vector(1, 1, 1)));
        scene.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(-20, -50, 50), new Vector(1, 1, -2)) //
                .setKl(0.00001).setKq(0.00000001));
        //scene.lights.add(new SpotLight(new Color(java.awt.Color.YELLOW),new Point3D(70, 65, -50),new Point3D(57, 50, -50).subtract(new Point3D(80, 80, -50))));
        scene.lights.add(new PointLight(new Color(500, 250, 250), new Point3D(10, -10, -130)) //
                .setKl(0.0005).setKq(0.0005));
        scene.lights.add( //
                new SpotLight(new Color(700, 400, 400), new Point3D(40, 40, 50), new Vector(-1, -1, -4)) //
                        .setKl(4E-4).setKq(2E-5));
        ImageWriter imageWriter = new ImageWriter("multipleLightSourcesSphere",  500, 500);
        Render render = new Render().setImageWriter(imageWriter).setCamera(camera).setRayTracer(new RayTracerBasic(scene));

        render.renderImage();
        render.writeToImage();
    }

}