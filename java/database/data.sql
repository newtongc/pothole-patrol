BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role, phone_number) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', '098-765-4321');
INSERT INTO users (username,password_hash,role, phone_number) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', '123-456-7890');

INSERT INTO potholes (address, zipcode, location_details, description, severity, reported_date, inspected_date, repair_date, can_contact, inspected, repaired, in_traffic, potential_damage, reporter_id, img_url)
VALUES ('4402 Montgomery Rd, Norwood, OH', '45212', 'I was just trying to get to work','Small pothole on side road', 1, '2023-08-06', '2023-08-07', NULL, true, true, false, false, false, 2, 'https://res.cloudinary.com/dd6n4zinx/image/upload/v1692277895/onkuutg4tsr8c6fbjm1z.jpg');


INSERT INTO potholes (address, zipcode, location_details, description, severity, reported_date, inspected_date, repair_date, can_contact, inspected, repaired, in_traffic, potential_damage, reporter_id, img_url)
VALUES ('4725 Montgomery Rd, Norwood, OH', '45212', 'In the parking lot', 'Multiple potholes on Park Ave.', 2, '2023-08-08', '2023-08-09', NULL, true, true, false, true, true, 1, 'https://res.cloudinary.com/dd6n4zinx/image/upload/v1692277783/y6dxpqdibzdwrj06jovm.jpg');

INSERT INTO potholes (address, zipcode, location_details, description, severity, reported_date, inspected_date, repair_date, can_contact, inspected, repaired, in_traffic, potential_damage, reporter_id, img_url)
VALUES ('1776 Mentor Ave, Cincinnati, OH', '45212', 'In the middle of the road', 'Large pothole on Main St.', 2, '2023-08-01', '2023-08-02', '2023-08-05', true, true, true, true, false, 2, 'https://res.cloudinary.com/dd6n4zinx/image/upload/v1692277831/trc6cbka1roodufqb8h2.jpg');


INSERT INTO potholes (address, zipcode, location_details, description, severity, reported_date, inspected_date, repair_date, can_contact, inspected, repaired, in_traffic, potential_damage, reporter_id, img_url)
VALUES ('4910 Para Dr, Cincinnati, OH', '45237', 'It popped my tire', 'Deep pothole near the intersection', 3, '2023-08-03', '2023-08-04', '2023-08-10', false, true, true, true, true, 1, 'https://res.cloudinary.com/dd6n4zinx/image/upload/v1692277983/kfpb5gbryzy1jzhccmgs.png');


INSERT INTO potholes (address, zipcode, location_details, description, severity, reported_date, inspected_date, repair_date, can_contact, inspected, repaired, in_traffic, potential_damage, reporter_id, img_url)
VALUES ('4500 Montgomery Rd, Cincinnati, OH', '45212', 'This one is literally a crater', 'Pothole near school entrance', 1, '2023-08-11', '2023-08-12', '2023-08-15', true, true, true, false, false, 1, 'https://res.cloudinary.com/dd6n4zinx/image/upload/v1692278050/vlu9fpwaiachmshg7vnq.jpg');


INSERT INTO potholes (address, zipcode, location_details, description, severity, reported_date, inspected_date, repair_date, can_contact, inspected, repaired, in_traffic, potential_damage, reporter_id, img_url)
VALUES ('5385 Ridge Ave, Cincinnati, OH','45213', 'I put rubber duckies in this one :D', 'Pothole on highway off-ramp', 3, '2023-08-14', '2023-08-15', NULL, false, true, false, true, true, 2, 'https://res.cloudinary.com/dd6n4zinx/image/upload/v1692277654/nibfxeh1cltv59p18ptl.jpg');


INSERT INTO potholes (address, zipcode, location_details, description, severity, reported_date, inspected_date, repair_date, can_contact, inspected, repaired, in_traffic, potential_damage, reporter_id, img_url)
VALUES ('3825 Edwards Rd, Cincinnati, OH', '45209', '@ Agave and Rye', 'Pothole near pedestrian crosswalk', 2, '2023-08-17', '2023-08-18', '2023-08-19', true, true, true, false, true, 1, 'https://res.cloudinary.com/dd6n4zinx/image/upload/v1692278209/uwatnmli0ynnkykvefu4.jpg');


INSERT INTO potholes (address, zipcode, location_details, description, severity, reported_date, inspected_date, repair_date, can_contact, inspected, repaired, in_traffic, potential_damage, reporter_id, img_url)
VALUES ('3200 Vandercar Way Suite 5, Cincinnati, OH','45209', 'In the ramen shop parking lot', 'Pothole causing road vibrations', 1, '2023-08-20', '2023-08-21', '2023-08-22', true, true, true, false, false, 2, 'https://res.cloudinary.com/dd6n4zinx/image/upload/v1692278275/usieg4fvmvqxtimddgju.jpg');


COMMIT TRANSACTION;


