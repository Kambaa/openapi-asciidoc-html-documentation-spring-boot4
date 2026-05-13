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


## API Documentation Options for a Spring Boot 4 Project

For a Spring Boot 4 project, choose the documentation approach based on your target use case:

| Goal | Best Option |
|---|---|
| Internal developer/testing docs | `springdoc-openapi` + Swagger UI |
| Modern embedded API UI | `springdoc-openapi` + Scalar |
| Static HTML artifact in CI/CD | Redocly CLI |
| Markdown docs committed to repo | OpenAPI Generator Markdown |
| Full developer portal | Docusaurus or MkDocs + OpenAPI plugin |
| Public polished API reference | Scalar or Redocly |

Links: 
- https://docs.spring.io/spring-restdocs/docs/current/reference/htmlsingle/
- https://github.com/spring-projects/spring-restdocs-samples/tree/main/restful-notes-spring-data-rest
- https://springdoc.org/v4/
- https://springdoc.org/plugins.html
- https://github.com/springdoc/springdoc-openapi-maven-plugin
