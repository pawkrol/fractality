## fractality
Simple graphic engine developed for simple rendering.

### Getting started

To render basic scene:

1. Create Game object and then create a window
    ```java  
    Game game = new Game();
    game.createWindow(1200, 800, "My fractality app", false);
    ```
    
2.  Next, define renderer and its config
    ```java
     Renderer renderer = new Renderer.RendererBuilder()
                .drawCallConfig(new DefaultDrawCallConfig())
                .renderConfig(new EnableCulling())
                .frameConfig(new ClearFrame())
                .camera(new Camera())
                .build();
    ```
    
3. And create your scene
    ```java
    Scene scene = new Scene();
    ```
    
4. Add to the scene objects that you like to render, for example:
    ```java
    Transform transform = new Transform();
    GameObject gameObject = new Triangle(scene);
    
    scene.getScenegraph()
         .setRoot(transform)
         .addChildren(gameObject);
    ```
    
5. Don't forget to add your renderer and scene to the Game object like so:
    ```java
    game.setScene(scene);
    game.setRenderer(renderer);
    ```
    
6. Now you are ready to start your rendering
    ```java
    game.start();
    ```
    
You can dynamically add or remove your objects from the scene.
