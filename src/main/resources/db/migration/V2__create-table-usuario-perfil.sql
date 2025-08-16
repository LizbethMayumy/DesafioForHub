create table usuarioperfil (
    usuarioid bigint not null,
    perfilid bigint not null,
    primary key (usuarioid, perfilid),
    constraint fk_usuarioperfil_usuarioid foreign key(usuarioid) references usuario(id),
    constraint fk_usuarioperfil_perfilid foreign key(perfilid) references perfil(id)
);
