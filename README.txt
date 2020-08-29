# RayTracingProject
A project to utilize sphere tracing to trace rays sent out from camera and the light source to render a photorealistic image with a mirrored ball, diffuse sphere, and an infinite checkerboard with proper shading and shadows.
To run:
- Run RayTracing.java, this will create a simple scene with two mirrored spheres and a diffuse sphere that should render in about a minute.

User Interface:
- Because the program takes a long time to render we didn't think the real time changes allowed for by a control panel GUI were useful and so all changes are made within the code. You can adjust the objects and light in the scene in the constructor for RayTracingPanel.java while the camera's position and direction are adjusted through RayTracing.java
- The size of the output image is determined by the width and height defined at the start of the draw method in Camera.java.
