create table if not exists courses(
    courseId int primary key,
    title varchar(50) not null,
    description varchar(100) ,
    price int,
    category_id int not null,
    foreign key(category_id) references categories(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);