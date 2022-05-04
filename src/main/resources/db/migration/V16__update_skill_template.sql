ALTER TABLE defence_skill RENAME TO defence_skill_template;
ALTER TABLE damage_skill RENAME TO damage_skill_template;

ALTER TABLE damage_skill_template DROP sub_id;
ALTER TABLE damage_skill_template DROP shop_id;
