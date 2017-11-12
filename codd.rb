#####CONNECTION#####

#Wrong URL
require 'open-uri'

remote_base_url = "http://nonsense/"

begin
   rpage = open("#{remote_base_url}/")
rescue SocketError=>e
   puts "#{ e.class.name }: #{ e.message }"
else
   data = rpage.read
end

 if data
   File.open("url.html", "w"){|f| f.write(rdata) }
 end


#####FILES#####

#Wrong permission in file
begin
	File.open("/etc/hosts") {|f| f << "example"}
rescue IOError => e
	puts "#{ e.class.name }: #{ e.message } - #{ e.backtrace[3]}"
end

#File does not exist
begin
	File.open("does/not/exist")
rescue Errno::ENOENT => e
	puts "#{ e.class.name }: #{ e.message } - #{ e.backtrace[1]}"
end

#####DATA TYPE#####

#Wrong type of data given by the user
number = false
while !number
   puts "Write a number>>"
   begin
     num = Kernel.gets.match(/\d+/)[0]
   rescue NoMethodError => e
     puts "#{ e.class.name }: #{ e.message }"
     puts "\tWrite a number this type\n"
   else  
     puts "#{num} + 1 is: #{num.to_i+1}"
     number = true
   end  
end
