package com.company;
import javax.sound.midi.*;

public class MiniMusicCmdLine {
    public static void main(String[] args) {
        MiniMusicCmdLine mini = new MiniMusicCmdLine();
        if (args.length < 2) {
            System.out.println("Не забудьте аргументы для инструмента и ноты!");
        } else {
            int instrument = Integer.parseInt(args[0]);
            int note = Integer.parseInt(args[1]);
            mini.play(instrument, note);
        }
    }

    public void play(int instrument, int note){
        try {
            Sequencer player = MidiSystem.getSequencer(); //Создаем синтезатор
            player.open();
            Sequence seq = new Sequence(Sequence.PPQ, 4); //Последовательность.
            Track track = seq.createTrack(); //Трек. Запрашиваем трек у последовательности

            MidiEvent event = null;

            ShortMessage first = new ShortMessage();
            first.setMessage(144, 1, instrument, 100);
            MidiEvent changeInstrument = new MidiEvent(first, 1); //Создаем Midi - событие
            track.add(changeInstrument);

            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, note, 100);
            MidiEvent noteOn = new MidiEvent(a, 1); //Создаем Midi - событие
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128,2,note,70);
            MidiEvent noteOff = new MidiEvent(b, 16);
            track.add(noteOff);
            player.setSequence(seq); //Передаем последовательность в синтезатор
            player.start(); // запускаем синтезатор

        }catch (Exception ex) { //Ловим все исключения
            ex.printStackTrace(); //Трассируем исключения
        }
    }
}

