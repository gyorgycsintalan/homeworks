create table listing (
    id binary(16) not null,
    title varchar(200) not null,
    description varchar(200) not null,
    location_id binary(16) not null,
    listing_price decimal(20,2) not null,
    currency varchar(3) not null,
    quantity int not null,
    listing_status int not null,
    marketplace int not null,
    upload_time date not null,
    owner_email_address varchar(200) not null
);

create table location (
    id binary(16) not null,
    manager_name varchar(200) not null,
    phone varchar(200) not null,
    address_primary varchar(200) not null,
    address_secondary varchar(200),
    country varchar(200) not null,
    town varchar(200) not null,
    postal_code varchar(200)
);

create table listing_status (
    id int not null,
    status_name varchar(20) not null
);

create table marketplace (
    id int not null,
    marketplace_name varchar(20) not null
);


alter table listing add primary key (id);
alter table listing add foreign key (location_id) references location(id);
alter table listing add foreign key (listing_status) references listing_status(id);
alter table listing add foreign key (marketplace) references marketplace(id);

alter table location add primary key (id);

alter table listing_status add primary key (id);

alter table marketplace add primary key (id);