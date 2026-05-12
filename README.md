# openapi-asciidoc-html-documentation-spring-boot4

### Requirements:

- JDK 25

This example project is for learning how to generate HTML documentation from swagger/openapi json
files. It uses various maven plugins to generate / fetch from runtime swagger.json file, and then
convert it to an asciidoc file, and lastly generate a HTML documentation page to statically serve.

to see it in action, run:

```shell
./mvnw clean package
```

Check out:

- [pom.xml](pom.xml) to see it(especially steps are done in maven exec plugin)
- [swagger.json](src/main/resources/static/swagger.json) generated via fetching it runtime
- [index.adoc](src/main/resources/static/index.adoc) asciidoc converted from swagger.json and
- [index.html](src/main/resources/templates/index.html) documentation static page.
