# News Portal Api


##### Project Description
This is a REST API news Portal Application


| route                              | purpose                                             |
|------------------------------------|-----------------------------------------------------|
| get("/")                           | returns a list of api routes                        |
| get("/department")                 | returns a list of all departments                   |
| get("/department/:id")             | returns a department with specific id               |
| post("/department/new")            | creates a new department                            |
| get("/users")                      | returns a list of users                             |
| get("/users/:id")                  | returns a user with the specific id                 |
| post("/departments/:id/users/new") | creates a user and associates it with department id |
| get("/news")                       | returns a list of general news created              |
| post("/news/new")                  | creates news item                                   |
| get("/department/news")            | returns a list of department news                   |
| post("/department/:id/news/new")   | creates news for a specific department              |
---

## Authors
- Charawey-X <charawey@gmail.com>
---

## Requirements
- ubuntu or any os with jdk
- IntelliJ


## Setup Instructions

* clone it to your desktop
```bash
 git clone  https://github.com/Charawey-X/news-portal.git
   ```
* Open the project with intellij

# Technologies Used

- java
- gradle
- postgres
- postman
- spark



## Contact Information

<a href="mailto:charawey@gmail.com">charawey@gmail.com</a>



## License & copyright

© Charawey-X

Licensed under the [MIT License](LICENSE)