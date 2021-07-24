select * from vote order by vestings_value desc

select author, permlink, sum(vestings_value) from vote group by author, permlink order by 3 desc

select author, permlink, count(1) from vote group by author, permlink order by 3 desc

select * from vote order by timestamp desc

select count(1) from vote

delete from vote;

update pisolog_state set last_seen_block =  49416655

select * from pisolog_state

commit