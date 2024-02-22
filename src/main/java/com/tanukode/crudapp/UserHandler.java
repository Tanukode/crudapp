package com.tanukode.crudapp;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.web.reactive.function.server.ServerResponse.from;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class UserHandler {

    private final UserRepository repository;

    public UserHandler(UserRepository repository){
        this.repository = repository;
    }

    public Mono<ServerResponse> greet(ServerRequest request){
        return ok().contentType(MediaType.TEXT_PLAIN).bodyValue("Hello");
    }

    //Get all users
    public Mono<ServerResponse> getUsers(ServerRequest request){
        Flux<User> users = repository.findAll();
        return ok().contentType(MediaType.APPLICATION_JSON).body(users, User.class);
    }

    //Get single user by id
    public Mono<ServerResponse> getUser(ServerRequest request){
        Mono<User> user = repository.findById(request.pathVariable("uid"));
        return ok().contentType(MediaType.APPLICATION_JSON).body(user, User.class);
    }

    //Save user
    public Mono<ServerResponse> createUser(ServerRequest request){
        Mono<User> userMono = request.bodyToMono(User.class);
        return userMono.flatMap(user->{
            Mono<User> result = repository.save(user);
            return result.then(ok().build());
        });
    }

    //Put user
    public Mono<ServerResponse> updateUser(ServerRequest request){
        String uidString = request.pathVariable("uid");
        Mono<Optional<User>> userFound = repository.findById(uidString).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalUser->{
                    if(optionalUser.isPresent()){
                        return request.bodyToMono(User.class).map(Optional::of).defaultIfEmpty(Optional.empty())
                                .flatMap(user->{
                                    User newUser = new User(optionalUser.get().getUid(), user.get().getName(),user.get().getEmail(),user.get().getPassword());
                                    return repository.save(newUser).map(Optional::of);
                                });
                    }
                    return Mono.empty();
                });

        return userFound.then(ok().build());
    }

    //Delete user
    public Mono<ServerResponse> deleteUser(ServerRequest request){
        return repository.deleteById(request.pathVariable("uid")).then(ok().build());
    }




}
