select * from vote order by vestings_value desc

select author, permlink, sum(vestings_value) from vote group by author, permlink order by 3 desc

select author, permlink, count(1) from vote group by author, permlink order by 3 desc

select * from vote order by timestamp desc

select * from vote where reward_value is not null order by reward_value desc

with voters as (
select voter,  reward_value / vestings_value as eff, reward_value from vote where reward_value is not null)
select voter, sum(eff) eff, count(1), sum(reward_value) cnt from voters group by voter order by 2 desc

select max(timestamp), min(timestamp) from vote

select voter, count(1) from vote where voter = 'bitwheeze' group by voter order by 2 desc 

select * from vote where voter = 'bitwheeze' order by "timestamp" desc

select avg(reward_value / vestings_value) from vote where reward_value is not null

delete from vote where timestamp < current_timestamp - interval '37 day' 

SELECT PERCENTILE_CONT(0.5) WITHIN GROUP(ORDER BY reward_value / vestings_value) FROM vote where reward_value is not null;

select count(1) from vote

delete from vote;

update pisolog_state set last_seen_block =  49416655; --49 618 255
49417920
update pisolog_state set last_seen_block =  49608255; --49 618 255


select * from pisolog_state

commit