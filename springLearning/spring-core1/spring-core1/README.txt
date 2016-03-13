= Building with Ant =

*  Description

   Course demonstration project. Presents a simplified project structure 
   consisting of two directories.


*  Directories
   
   /src

     Java source and configuration files

   /lib

     Third-party libraries.


*  Build configuration

   - Apache Ant

     A basic ant build configuration is supplied with two basic targets.

     - "ant compile"

     - "ant clean"

   - Apache Maven 2

     A maven 2 project configuration is supplied. The Maven 2 build tool
     provides a selection of build targets such as compile, test, package,
     and install.

     The project is customized with dependency selections for Spring and
     JPA/Hibernate development.
     
     The dependencyManagement section enumerates a working combination of
     Spring, Hibernate, and third-party libraries.

     The dependency section selects a subset of the libraries from
     dependencyManagement in support of a basic Spring project.

     To add more capabilities, for instance Spring AOP or JPA/Hibernate
     persistence, reference libraries from dependencyManagement in the
     general dependencies section.
