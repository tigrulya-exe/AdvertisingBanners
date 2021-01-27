create table Categories (
    id          int auto_increment primary key,
    name        varchar(255) not null unique,
    requestName varchar(255) not null unique,
    isDeleted   bool         not null default false
);

create table Banners (
    id         int auto_increment primary key,
    name       varchar(255)  not null unique,
    price      decimal(8, 2) not null,
    categoryId integer       not null references Categories (id),
    content    text          not null,
    isDeleted  bool          not null default false
);

create table Requests (
    id        int auto_increment primary key,
    bannerId  int          not null references Banners (id),
    userAgent text         not null,
    ipAddress varchar(255) not null,
    date      datetime     not null
);