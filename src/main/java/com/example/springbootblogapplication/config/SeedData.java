package com.example.springbootblogapplication.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.example.springbootblogapplication.models.Account;
import com.example.springbootblogapplication.models.Authority;
import com.example.springbootblogapplication.models.Post;
import com.example.springbootblogapplication.repositories.AuthorityRepository;
import com.example.springbootblogapplication.services.AccountService;
import com.example.springbootblogapplication.services.FileService;
import com.example.springbootblogapplication.services.PostService;

@Component
public class SeedData implements CommandLineRunner {

        @Autowired
        private FileService fileService;

        @Autowired
        private PostService postService;

        @Autowired
        private AccountService accountService;

        @Autowired
        private AuthorityRepository authorityRepository;

        @Override
        public void run(String... args) throws Exception {

                fileService.init();

                Page<Post> posts = postService.getAll(0, 10, "createdAt", "DESC", null);

                if (posts.getTotalElements() == 0) {

                        Authority user = new Authority();
                        user.setName("ROLE_USER");
                        authorityRepository.save(user);

                        Authority admin = new Authority();
                        admin.setName("ROLE_ADMIN");
                        authorityRepository.save(admin);

                        Account account1 = Account
                                        .builder()
                                        .firstName("Mugabo")
                                        .lastName("Patience")
                                        .email("paty.mugabo@gmail.com")
                                        .password("password")
                                        .build();

                        Set<Authority> authorities1 = new HashSet<>();
                        authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
                        account1.setAuthorities(authorities1);

                        Account account2 = Account
                                        .builder()
                                        .firstName("Admin")
                                        .lastName("Mugabo")
                                        .email("admin@gmail.com")
                                        .password("password")
                                        .build();

                        Set<Authority> authorities2 = new HashSet<>();
                        authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
                        account2.setAuthorities(authorities2);

                        accountService.save(account1);
                        accountService.save(account2);

                        // In-depth blog posts
                        Post post1 = Post
                                        .builder()
                                        .title("Understanding Spring Boot for Beginners")
                                        .body("Spring Boot is an open-source Java framework that simplifies building, configuring, and deploying Java web applications. Unlike the standard Spring Framework, which requires complex configuration, Spring Boot auto-configures applications based on project dependencies, allowing developers to focus on writing code. With features like embedded servers, opinionated defaults, and Spring Boot Starters, beginners can get projects up and running in minutes. This post covers key concepts including the importance of Spring Boot Starter projects, how to create a project using Spring Initializr, and how dependency injection works within the framework. Additionally, we’ll explore the benefits of Spring Boot’s auto-configuration and its integration with other popular Spring modules.")
                                        .account(account1)
                                        .imageFilePath("spring-boot-guide.png")
                                        .build();

                        Post post2 = Post
                                        .builder()
                                        .title("Best Practices in REST API Design")
                                        .body("In modern software development, RESTful APIs have become essential for building scalable and efficient web applications. REST (Representational State Transfer) is an architectural style that leverages standard HTTP methods to manage resources, making APIs intuitive and accessible. Designing a robust REST API requires following best practices to ensure security, consistency, and ease of use. This post discusses essential practices like using HTTP status codes appropriately, structuring endpoints logically, and employing query parameters for flexibility. We also cover considerations for securing APIs with token-based authentication, optimizing response times, and documenting APIs effectively using tools like Swagger. Proper REST API design can significantly improve the user experience, making this knowledge invaluable for developers.")
                                        .account(account2)
                                        .imageFilePath("api-best-practices.webp")
                                        .build();

                        Post post3 = Post
                                        .builder()
                                        .title("Introduction to Microservices Architecture")
                                        .body("Microservices architecture is a software design style that divides an application into a collection of loosely coupled services. Each service performs a distinct function, can be developed independently, and communicates over a network via lightweight protocols such as HTTP or messaging queues. This post explains the benefits of microservices, including scalability, fault isolation, and the ability to deploy components independently. We also address common challenges, such as data management, service discovery, and network latency. Additionally, we’ll explore strategies for successful microservices design, including using a gateway pattern, implementing API versioning, and organizing services around business capabilities. By understanding the principles of microservices, developers can build applications that scale with ease and adapt to evolving business needs.")
                                        .account(account1)
                                        .imageFilePath("microservices.webp")
                                        .build();

                        Post post4 = Post
                                        .builder()
                                        .title("Understanding Docker for Deployment")
                                        .body("Docker has revolutionized the way applications are deployed, allowing developers to package applications with all dependencies into a standardized container format. Containers are lightweight, portable, and ensure consistent behavior across various environments, from development to production. This post provides a detailed guide on Docker’s core concepts, such as images, containers, and volumes, as well as how to write effective Dockerfiles. We’ll walk through steps for containerizing a simple Java application, building an image, and running the application as a container. Additionally, we cover the importance of Docker Compose for managing multi-container applications and provide tips for optimizing Dockerfiles to reduce image size and improve build times. Docker is a powerful tool for developers and DevOps teams, transforming how applications are deployed and maintained.")
                                        .account(account2)
                                        .imageFilePath("docker-deployment.png")
                                        .build();

                        postService.save(post1);
                        postService.save(post2);
                        postService.save(post3);
                        postService.save(post4);
                }
        }
}
