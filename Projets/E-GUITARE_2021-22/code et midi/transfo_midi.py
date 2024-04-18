import mido
from mido import MidiFile,MidiTrack

mid = MidiFile('smoke_on_the_water.mid')


liste_notes = []
liste_timing = []
liste_type = []
liste_nb=[]
liste_finale=[]


for i in (mid.tracks):
	for salut in(i):
		if not salut.is_meta:
			liste_nb.append(salut.note)
		if len(liste_nb)!=0:
			break

for i in(mid.tracks):
	for salut in(i):
		valide= False
		if not salut.is_meta:
			a=0
			for y in range (len(liste_nb)):
				if liste_nb[y]!=salut.note and valide == False:
					a+=1
				if a==len(liste_nb):
					liste_nb.append(salut.note)
					valide = True


for salut in (mid.tracks):
	for i in (salut):
		if not i.is_meta:
		
			liste_notes.append(i.note)
			liste_timing.append(i.time)
			liste_type.append(i.type)

timing_total =0
for i in range(len(liste_timing)):
	timing_total += liste_timing[i]


p = 0 
while timing_total>>0:

	if p<=len(liste_type)-2:
		current_type = liste_type[p+1]
	
	if p<=len(liste_type)-3:
		future_type = liste_type[p+2]
		

	
	if liste_timing[p]>>0  or current_type==future_type:

		if liste_type[p]=='note_off' and p<=len(liste_type)-1:
			liste_finale.append(liste_notes[p])

		elif current_type==future_type and p<=len(liste_type)-1:
			liste_finale.append(liste_notes[p-1])

		elif p>=len(liste_type)-2:
			liste_finale.append(0)

		else:
			liste_finale.append(0)
		timing_total-=24
		liste_timing[p]-=24
	if liste_timing[p]<=0:
		p+=1
	
liste_arduino = []
for i in range(len(liste_finale)):
	if liste_finale[i]==0:
		liste_arduino.append(0)
	if liste_finale[i]==50:
		liste_arduino.append(1)
	if liste_finale[i]==53:
		liste_arduino.append(2)
	if liste_finale[i]==55:
		liste_arduino.append(3)
	if liste_finale[i]==56:
		liste_arduino.append(4)
print(liste_arduino)








