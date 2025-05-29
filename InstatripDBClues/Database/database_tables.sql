create table if not exists User
(
    UserID        bigint auto_increment
        primary key,
    username      varchar(20)                               null,
    password_hash varchar(100)                              null,
    type          enum ('client', 'tour_office', 'partner') null
);

create table if not exists Customer
(
    customerID bigint auto_increment
        primary key,
    firstName  varchar(50) null,
    lastName   varchar(50) null,
    email      varchar(70) null,
    location   varchar(50) null,
    UserID     bigint      null,
    constraint Customer_ibfk_1
        foreign key (UserID) references User (UserID)
);

create index UserID
    on Customer (UserID);

create table if not exists CustomerPhone
(
    Phone      varchar(20) not null
        primary key,
    CustomerID bigint      null,
    constraint CustomerPhone_ibfk_1
        foreign key (CustomerID) references Customer (customerID)
);

create index CustomerID
    on CustomerPhone (CustomerID);

create table if not exists ExtPartner
(
    PartnerID   bigint auto_increment
        primary key,
    name        varchar(30)                                 null,
    address     varchar(70)                                 null,
    location    varchar(50)                                 null,
    schedule    text                                        null,
    phone       varchar(20)                                 null,
    email       varchar(70)                                 null,
    UserID      bigint                                      null,
    description text                                        null,
    partnerType enum ('Χώρος Διαμονής', 'Άλλος συνεργάτης') null,
    constraint ExtPartner_ibfk_1
        foreign key (UserID) references User (UserID)
);

create table if not exists Business
(
    BusinessID bigint auto_increment
        primary key,
    type       varchar(30) not null,
    PartnerID  bigint      null,
    constraint Business_ibfk_1
        foreign key (PartnerID) references ExtPartner (PartnerID)
);

create index PartnerID
    on Business (PartnerID);

create index UserID
    on ExtPartner (UserID);

create table if not exists Guide
(
    partnerID bigint      null,
    guideID   bigint      not null
        primary key,
    expertise varchar(30) null,
    constraint Guide_ibfk_1
        foreign key (partnerID) references ExtPartner (PartnerID)
);

create index partnerID
    on Guide (partnerID);

create table if not exists LivingQuarter
(
    quarterID     bigint auto_increment
        primary key,
    amenities     text                null,
    accessibility enum ('Ναι', 'Οχι') null,
    UserID        bigint              null,
    partnerID     bigint              null,
    constraint LivingQuarter_ibfk_1
        foreign key (UserID) references User (UserID),
    constraint fkLivExt
        foreign key (partnerID) references ExtPartner (PartnerID)
);

create table if not exists Hotel
(
    QuarterID bigint auto_increment
        primary key,
    stars     int null,
    constraint Hotel_ibfk_1
        foreign key (QuarterID) references LivingQuarter (quarterID)
);

create index UserID
    on LivingQuarter (UserID);

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
    amenities set ('Θέα στη θάλασσα', 'θέα στο βουνό', 'Πρόσβαση για Άτομα Με Ειδικές Ανάγκες', 'Επιτρέπονται τα κατοικίδια', 'Πρωινό στο δωμάτιο', 'Υπηρεσία καθαρισμού', 'Wi-Fi', 'Ιδιωτική Πισίνα', 'Ορθοπεδικό στρώμα', 'Mini bar', 'Σεντόνια', 'Πετσέτες μπάνιου', 'Τηλεόραση', 'Κουζίνα', 'Μπαλκόνι', 'Πλυντήριο ρούχων', 'A/C') null,
    capacity  bigint                                                                                                                                                                                                                                                                                                                   null,
    type      enum ('Σουίτα', 'Δωμάτιο', 'Διαμέρισμα', 'Μεζονέτα', 'Bungalow')                                                                                                                                                                                                                                                         null,
    quarterID bigint                                                                                                                                                                                                                                                                                                                   null,
    constraint Room_ibfk_1
        foreign key (quarterID) references LivingQuarter (quarterID)
);

create index quarterID
    on Room (quarterID);

create table if not exists RoomsToLet
(
    QuarterID bigint auto_increment
        primary key,
    rating    varchar(100) null,
    constraint RoomsToLet_ibfk_1
        foreign key (QuarterID) references LivingQuarter (quarterID)
);

create table if not exists TourAgency
(
    AgencyID       bigint auto_increment
        primary key,
    name           varchar(30)              null,
    email          varchar(70)              null,
    location       varchar(50)              null,
    address        varchar(70)              null,
    UserID         bigint                   null,
    paymentMethods set ('Καρτα', 'Μετρητά') null,
    constraint TourAgency_ibfk_1
        foreign key (UserID) references User (UserID)
);

create table if not exists Package
(
    PackageID       bigint auto_increment
        primary key,
    startDate       date                                                               not null,
    endDate         date                                                               not null,
    location        varchar(50)                                                        not null,
    price           double                                                             null,
    description     text                                                               null,
    files           blob                                                               null,
    status          enum ('Ολοκληρωμένο', 'Σε εξέλιξη', 'Ενεργοποιημένο', 'Ακυρωμένο') not null,
    AgencyID        bigint                                                             null,
    maxParticipants bigint                                                             null,
    constraint Package_ibfk_1
        foreign key (AgencyID) references TourAgency (AgencyID)
);

create table if not exists HistoryPackage
(
    HpID      bigint auto_increment
        primary key,
    rdate     date default (curdate()) null,
    PackageID bigint                   null,
    constraint HistoryPackage_ibfk_1
        foreign key (PackageID) references Package (PackageID)
);

create index PackageID
    on HistoryPackage (PackageID);

create index AgencyID
    on Package (AgencyID);

create definer = avnadmin@`%` trigger check_beforeStatus
    before update
    on Package
    for each row
begin
    declare counter int;
    select count(*) into counter from quarterPackage where quarterPackage.packageID=new.PackageID && quarterPackage.status = 'Σε ισχύ';
    if(DATEDIFF(new.endDate, new.startDate)>0 and counter<=0 and NEW.status='Ενεργοποιημένο') then
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ένα πακέτο που δεν αφορά μονοήμερη εκδρομή \n δεν μπορεί να οριστικοποιηθεί\n χωρίς συνεργασία με κάποιο χώρο διαμονής';
    end if;
end;

create table if not exists Reservation
(
    ResID      bigint auto_increment
        primary key,
    people     bigint                                                    null,
    status     enum ('Ολοκληρωμένη', 'Ακυρωμένη', 'Σε αναμονή πληρωμής') null,
    rdate      date default (curdate())                                  null,
    CustomerID bigint                                                    null,
    PackageID  bigint                                                    null,
    RoomID     bigint                                                    null,
    constraint Reservation_ibfk_1
        foreign key (CustomerID) references Customer (customerID),
    constraint Reservation_ibfk_2
        foreign key (PackageID) references Package (PackageID),
    constraint Reservation_ibfk_3
        foreign key (RoomID) references Room (RoomID)
);

create index CustomerID
    on Reservation (CustomerID);

create index PackageID
    on Reservation (PackageID);

create index RoomID
    on Reservation (RoomID);

create table if not exists ReservationBucket
(
    buck        bigint auto_increment
        primary key,
    PackageId   bigint not null,
    CustomerId  bigint not null,
    people      int    null,
    description text   null,
    price       double null,
    location    text   null,
    constraint PackBucket
        foreign key (PackageId) references Package (PackageID),
    constraint PackCust
        foreign key (CustomerId) references Customer (customerID)
);

create index UserID
    on TourAgency (UserID);

create table if not exists TourPhone
(
    Phone    varchar(20) not null
        primary key,
    AgencyID bigint      null,
    constraint TourPhone_ibfk_1
        foreign key (AgencyID) references TourAgency (AgencyID)
);

create index AgencyID
    on TourPhone (AgencyID);

create table if not exists monument
(
    partnerID bigint      not null
        primary key,
    type      varchar(30) null,
    constraint monument_ibfk_1
        foreign key (partnerID) references ExtPartner (PartnerID)
);

create table if not exists partnerPackage
(
    partnerID bigint                                      not null,
    packageID bigint                                      not null,
    status    enum ('Σε ισχύ', 'Ακυρωμένη', 'Σε αναμονή') null,
    requestID bigint auto_increment
        primary key,
    constraint partnerID
        unique (partnerID, packageID),
    constraint ibfk_package
        foreign key (packageID) references Package (PackageID),
    constraint ibfk_partner
        foreign key (partnerID) references ExtPartner (PartnerID)
);

create table if not exists quarterPackage
(
    packageID bigint                                      not null,
    quarterID bigint                                      not null,
    status    enum ('Σε ισχύ', 'Ακυρωμένη', 'Σε Αναμονή') null,
    requestID bigint auto_increment
        primary key,
    constraint packageID
        unique (packageID, quarterID),
    constraint ib_fk_package
        foreign key (packageID) references Package (PackageID),
    constraint ib_fk_quarter
        foreign key (quarterID) references LivingQuarter (quarterID)
);

create or replace definer = avnadmin@`%` view PartnerCooperationView as
select `defaultdb`.`ExtPartner`.`name`          AS `cooperator_name`,
       `defaultdb`.`partnerPackage`.`packageID` AS `package_coop`,
       `defaultdb`.`partnerPackage`.`status`    AS `cooperation_status`,
       `defaultdb`.`partnerPackage`.`requestID` AS `cooperationID`,
       `pkg`.`AgencyID`                         AS `AgencyID`,
       'partner'                                AS `source_type`
from ((`defaultdb`.`ExtPartner` join `defaultdb`.`partnerPackage` on ((`defaultdb`.`ExtPartner`.`PartnerID` =
                                                                       `defaultdb`.`partnerPackage`.`partnerID`))) join `defaultdb`.`Package` `pkg`
      on ((`defaultdb`.`partnerPackage`.`packageID` = `pkg`.`PackageID`)))
union all
select `defaultdb`.`ExtPartner`.`name`          AS `cooperator_name`,
       `defaultdb`.`quarterPackage`.`packageID` AS `package_coop`,
       `defaultdb`.`quarterPackage`.`status`    AS `cooperation_status`,
       `defaultdb`.`quarterPackage`.`requestID` AS `cooperationID`,
       `pkg`.`AgencyID`                         AS `AgencyID`,
       'quarter'                                AS `source_type`
from (((`defaultdb`.`LivingQuarter` join `defaultdb`.`ExtPartner` on ((`defaultdb`.`LivingQuarter`.`partnerID` =
                                                                       `defaultdb`.`ExtPartner`.`PartnerID`))) join `defaultdb`.`quarterPackage`
       on ((`defaultdb`.`LivingQuarter`.`quarterID` =
            `defaultdb`.`quarterPackage`.`quarterID`))) join `defaultdb`.`Package` `pkg`
      on ((`defaultdb`.`quarterPackage`.`packageID` = `pkg`.`PackageID`)));

