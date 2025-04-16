create table if not exists User
(
    UserID        bigint auto_increment
        primary key,
    username      varchar(20)                                                        ,
    password_hash varchar(100)                                                       ,
    type          enum ('client', 'tour_office', 'partner', 'accommodation_manager')
);

create table if not exists Customer
(
    customerID bigint auto_increment
        primary key,
    firstName  varchar(50) ,
    lastName   varchar(50) ,
    email      varchar(70) ,
    location   varchar(50) ,
    UserID     bigint      ,
    constraint Customer_ibfk_1
        foreign key (UserID) references User (UserID)
);


create table if not exists CustomerPhone
(
    Phone      varchar(20) not null
        primary key,
    CustomerID bigint      ,
    constraint CustomerPhone_ibfk_1
        foreign key (CustomerID) references Customer (customerID)
);


create table if not exists ExtPartner
(
    PartnerID bigint auto_increment
        primary key,
    name      varchar(30) ,
    address   varchar(70) ,
    location  varchar(50) ,
    schedule  text        ,
    phone     varchar(20) ,
    email     varchar(70) ,
    UserID    bigint      ,
    constraint ExtPartner_ibfk_1
        foreign key (UserID) references User (UserID)
);

create table if not exists Business
(
    BusinessID bigint auto_increment
        primary key,
    type       varchar(30) not null,
    PartnerID  bigint      ,
    constraint Business_ibfk_1
        foreign key (PartnerID) references ExtPartner (PartnerID)
);


create table if not exists Guide
(
    partnerID bigint      ,
    guideID   bigint      not null
        primary key,
    expertise varchar(30) ,
    constraint Guide_ibfk_1
        foreign key (partnerID) references ExtPartner (PartnerID)
);



create table if not exists LivingQuarter
(
    quarterID     bigint auto_increment
        primary key,
    amenities     text                ,
    accessibility enum ('Ναι', 'Οχι') ,
    UserID        bigint              ,
    partnerID     bigint              ,
    constraint LivingQuarter_ibfk_1
        foreign key (UserID) references User (UserID),
    constraint fkLivExt
        foreign key (partnerID) references ExtPartner (PartnerID)
);

create table if not exists Hotel
(
    QuarterID bigint auto_increment
        primary key,
    stars     int ,
    constraint Hotel_ibfk_1
        foreign key (QuarterID) references LivingQuarter (quarterID)
);


create table if not exists Museum
(
    PartnerID bigint auto_increment
        primary key,
    type      varchar(30) not null,
    constraint Museum_ibfk_1
        foreign key (PartnerID) references ExtPartner (PartnerID)
);

create table if not exists Room
(
    RoomID    bigint auto_increment
        primary key,
    amenities set ('Θέα στη θάλασσα', 'θέα στο βουνό', 'Πρόσβαση για Άτομα Με Ειδικές Ανάγκες', 'Επιτρέπονται τα κατοικίδια', 'Πρωινό στο δωμάτιο', 'Υπηρεσία καθαρισμού', 'Wi-Fi', 'Ιδιωτική Πισίνα', 'Ορθοπεδικό στρώμα', 'Mini bar', 'Σεντόνια', 'Πετσέτες μπάνιου', 'Τηλεόραση', 'Κουζίνα', 'Μπαλκόνι', 'Πλυντήριο ρούχων', 'A/C') ,
    capacity  bigint                                                                                                                                                                                                                                                                                                                   ,
    type      enum ('Σουίτα', 'Δωμάτιο', 'Διαμέρισμα', 'Μεζονέτα', 'Bungalow')                                                                                                                                                                                                                                                         ,
    quarterID bigint                                                                                                                                                                                                                                                                                                                   ,
    constraint Room_ibfk_1
        foreign key (quarterID) references LivingQuarter (quarterID)
);


create table if not exists RoomsToLet
(
    QuarterID bigint auto_increment
        primary key,
    rating    varchar(100) ,
    constraint RoomsToLet_ibfk_1
        foreign key (QuarterID) references LivingQuarter (quarterID)
);

create table if not exists TourAgency
(
    AgencyID       bigint auto_increment
        primary key,
    name           varchar(30)              ,
    email          varchar(70)              ,
    location       varchar(50)              ,
    address        varchar(70)              ,
    UserID         bigint                   ,
    paymentMethods set ('Καρτα', 'Μετρητά') ,
    constraint TourAgency_ibfk_1
        foreign key (UserID) references User (UserID)
);

create table if not exists Package
(
    PackageID   bigint auto_increment
        primary key,
    startDate   date                                                               not null,
    endDate     date                                                               not null,
    location    varchar(50)                                                        not null,
    price       int                                                                not null,
    description text                                                               ,
    files       blob                                                               ,
    status      enum ('Ολοκληρωμένο', 'Σε εξέλιξη', 'Ενεργοποιημένο', 'Ακυρωμένο') not null,
    AgencyID    bigint                                                             ,
    constraint Package_ibfk_1
        foreign key (AgencyID) references TourAgency (AgencyID)
);

create table if not exists Feedback
(
    FeedbackID bigint auto_increment
        primary key,
    grade      int    not null,
    content    text   ,
    fdate      date   ,
    PackageID  bigint ,
    CustomerID bigint ,
    quarterID  bigint ,
    constraint Feedback_ibfk_1
        foreign key (PackageID) references Package (PackageID),
    constraint Feedback_ibfk_2
        foreign key (CustomerID) references Customer (customerID),
    constraint Feedback_ibfk_3
        foreign key (quarterID) references LivingQuarter (quarterID)
);

create table if not exists Answer
(
    AnswerID   bigint auto_increment
        primary key,
    content    text   ,
    fdate      date   ,
    FeedbackID bigint ,
    AgencyID   bigint ,
    constraint Answer_ibfk_1
        foreign key (FeedbackID) references Feedback (FeedbackID),
    constraint Answer_ibfk_2
        foreign key (AgencyID) references TourAgency (AgencyID)
);



create table if not exists HistoryPackage
(
    HpID      bigint auto_increment
        primary key,
    rdate     date   not null,
    PackageID bigint ,
    constraint HistoryPackage_ibfk_1
        foreign key (PackageID) references Package (PackageID)
);


create table if not exists Reservation
(
    ResID      bigint auto_increment
        primary key,
    people     bigint                                                    ,
    status     enum ('Ολοκληρωμένη', 'Ακυρωμένη', 'Σε αναμονή πληρωμής') ,
    rdate      date                                                      not null,
    CustomerID bigint                                                    ,
    PackageID  bigint                                                    ,
    RoomID     bigint                                                    ,
    constraint Reservation_ibfk_1
        foreign key (CustomerID) references Customer (customerID),
    constraint Reservation_ibfk_2
        foreign key (PackageID) references Package (PackageID),
    constraint Reservation_ibfk_3
        foreign key (RoomID) references Room (RoomID)
);


create table if not exists TourPhone
(
    Phone    varchar(20) not null
        primary key,
    AgencyID bigint      ,
    constraint TourPhone_ibfk_1
        foreign key (AgencyID) references TourAgency (AgencyID)
);



create table if not exists customerPayment
(
    customerID bigint      not null,
    cardNumber varchar(32) not null,
    ownerName  text        ,
    cvv        varchar(3)  ,
    exp_date   date        ,
    primary key (customerID, cardNumber),
    constraint customerPayment_ibfk_1
        foreign key (customerID) references Customer (customerID)
);

create table if not exists monument
(
    partnerID bigint      not null
        primary key,
    type      varchar(30) ,
    constraint monument_ibfk_1
        foreign key (partnerID) references ExtPartner (PartnerID)
);

create table if not exists partnerPackage
(
    partnerID bigint                                      not null,
    packageID bigint                                      not null,
    status    enum ('Σε ισχύ', 'Ακυρωμένη', 'Σε αναμονή') ,
    primary key (partnerID, packageID),
    constraint partnerPackage_ibfk_1
        foreign key (partnerID) references ExtPartner (PartnerID),
    constraint partnerPackage_ibfk_2
        foreign key (packageID) references Package (PackageID)
);


create table if not exists quarterPackage
(
    packageID bigint                                      not null,
    quarterID bigint                                      not null,
    status    enum ('Σε ισχύ', 'Ακυρωμένη', 'Σε Αναμονή') ,
    primary key (packageID, quarterID),
    constraint quarterPackage_ibfk_1
        foreign key (packageID) references Package (PackageID),
    constraint quarterPackage_ibfk_2
        foreign key (quarterID) references LivingQuarter (quarterID)
);


