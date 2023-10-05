package com.wellplayed.gaming.store;

import com.wellplayed.gaming.store.models.*;
import com.wellplayed.gaming.store.repositories.BuyRepository;
import com.wellplayed.gaming.store.repositories.ClientRepository;
import com.wellplayed.gaming.store.repositories.ComponentRepository;
import com.wellplayed.gaming.store.repositories.TicketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class WellPlayedApplication {

    private LocalDateTime localDateTime = LocalDateTime.now();

    public static void main(String[] args) {
        SpringApplication.run(WellPlayedApplication.class , args);
    }

    @Bean
    public CommandLineRunner initData(ClientRepository clientRepository, ComponentRepository componentRepository, BuyRepository buyRepository, TicketRepository ticketRepository) {
        return args -> {
//           Client julianBrunelli = new Client("JulianBrunelli@outlook.com", "1234", 43317950, 1925, "36 e/ 123 y 124", "2216178731");
//            clientRepository.save(julianBrunelli);
//
//            Component mouseHyperX1 = new Component("Mouse", "HyperX", "Pulsefire Core - RGB Gaming Mouse", "The HyperX Pulsefire Core delivers the essentials for gamers looking for a solid, comfortable, wired RGB gaming mouse. The Pixart 3327 optical sensor gives players precise, smooth tracking with no hardware acceleration, and has native DPI settings up to 6200 DPI. The ergonomically-designed Pulsefire Core has textured side grips for a comfortable, no-slip grip, and its symmetrical shape is suitable for both palm and claw grip.", Arrays.asList("Pixart 3327 optical sensor","6200 DPI / 220 IPS / 30G", "Customizable with HyperX NGENUITY software", "Weight: 87g"), Arrays.asList("Pink"), Arrays.asList("https://i.imgur.com/I6NjQxV.jpg", "https://i.imgur.com/hz9vCpq.jpg", "https://i.imgur.com/ePu6lxu.jpg", "https://i.imgur.com/7NTesn7.jpg", "https://i.imgur.com/kHj9IF3.jpg"), 29.99, 5);
//
//            Component mouseHyperX2 = new Component("Mouse", "HyperX", "Pulsefire Haste - Itachi Edition - Gaming Mouse", "The HyperX Pulsefire Haste is built for elite gamers looking to gain every fraction of a second possible in their quest to be the best. Weighing in at 59 grams, this full-featured, responsive honeycomb shell mouse has everything you need, just without the extra weight. Blaze your own trail with this limited-edition design inspired by Itachi’s time with the Akatsuki.", Arrays.asList("Limited Itachi Edition", "Ultra-light and built for speed", "Customizable with HyperX NGENUITY Software", "Grip Tape Included"), Arrays.asList("Black-Red"), Arrays.asList("https://i.imgur.com/4lLRj15.jpg", "https://i.imgur.com/RNPLMq5.jpg", "https://i.imgur.com/PCAFG6G.jpg", "https://i.imgur.com/NMLYnEp.jpg", "https://i.imgur.com/oPJNB09.jpg"), 59.99, 100);
//
//            Component keyboardHyperX1 = new Component("Keyboard", "HyperX", "Alloy Elite 2 - Mechanical Gaming Keyboard", "For gamers, streamers, and multi-taskers who need to have more control at their fingertips, the HyperX Alloy Elite 2 is the keyboard for you. With dedicated media keys and a large volume wheel, this fully-featured gaming keyboard’s ready for everything from video editing to watching movies. It’s built with ultra-reliable HyperX mechanical switches balanced for speed and responsiveness, so you can trust your key inputs. The switches’ exposed LEDs pair with the translucent HyperX Pudding Keycaps to give your RGB lighting extra brightness compared to solid-color keycaps. Customize the signature light bar and create lighting profiles with intuitive HyperX NGENUITY software for a brilliant display of colors. Use the software to personalize your setup further with custom Game Mode and macros. A USB 2.0 pass-through provides an extra USB port for your multi-tasking needs. The Alloy Elite 2’s sturdy steel frame makes it durable enough for work and play day in and day out.\n", Arrays.asList("RGB Backlighting", "Customizable with NGENUITY Software", "HyperX Pudding Keycaps (ABS)"), Arrays.asList("Black"), Arrays.asList("https://hyperx.com/cdn/shop/files/hyperx_alloy_elite_2_us_1_top_down_renamed_4_1080x.jpg?v=1688317920", "https://hyperx.com/cdn/shop/files/hyperx_alloy_elite_2_us_2_angled_left_renamed_2_1080x.jpg?v=1688317920", "https://hyperx.com/cdn/shop/files/hyperx_alloy_elite_2_us_4_side_renamed_0_1080x.jpg?v=1688317920", "https://hyperx.com/cdn/shop/files/hyperx_alloy_elite_2_us_2_angled_left_renamed_2_1080x.jpg?v=1688317920"), 190.99, 8);
//
//            Component keyboardHyperX2 = new Component("Keyboard", "HyperX", "Alloy Core RGB Membrane Gaming Keyboard", "Featuring HyperX’s signature radiant light bar, and smooth, dynamic RGB lighting effects, the HyperX Alloy Core RGB™ is ideal for gamers looking to enhance their keyboard’s style and performance without breaking the bank. With six different lighting effects and three brightness levels, it balances both brilliance and budget. Crafted with a durable, reinforced plastic frame, the Alloy Core RGB was constructed for stability and reliability for gamers who want a keyboard that will last.", Arrays.asList("RGB Backlighting", "Quiet, responsive keys", "Signature light bar and dynamic RGB lighting effects"), Arrays.asList("Black"), Arrays.asList("https://i.imgur.com/nKVSIp8.jpg", "https://i.imgur.com/kZGa8zW.jpg", "https://i.imgur.com/tmrLe1W.jpg"), 39.99, 100);
//
//            Component microphoneHyperX1 = new Component("Microphone", "HyperX", "QuadCast S – USB Condenser Gaming Microphone", "The HyperX QuadCast™ S is a USB condenser microphone that both sounds great and looks great. The supremely stunning RGB lighting and dynamic effects will add style and flair to any stream or setup and is customizable via HyperX NGENUITY software. The QuadCast S is an all-inclusive mic, featuring an anti-vibration shock mount to help quiet the rumbles of daily life and a built-in pop filter to muffle plosive sounds. Instantly know your mic status with the LED indicator, and simply tap-to-mute to avoid awkward broadcasting accidents.", Arrays.asList("RGB Backlighting", "Customizable with NGENUITY Software", "Tap-to-Mute Sensor with LED indicator", "Stereo, Omnidirectional, Cardioid, Bidirectional"), Arrays.asList("Black", "White"), Arrays.asList("https://i.imgur.com/4cMXDJl.jpg", "https://i.imgur.com/n4yrosp.jpg",  "https://i.imgur.com/cKeq3y1.jpg", "https://i.imgur.com/H2wMzd3.jpg"), 129.99, 100);
//
//            Component microphoneHyperX2 = new Component("Microphone", "HyperX", "DuoCast USB Microphone", "Featuring an elegant design, a low-profile shock mount, and a tasteful RGB light ring, the HyperX DuoCast is a full-featured USB microphone that’s built for gaming, working, and content creation. It shares the fan-favorite features of its HyperX mic siblings, such as tap-to-mute, and the vibrant LED mic mute indicator, but also brings its own unique, subtle style. A low-profile shock mount complements the sleek, minimalist aesthetic, while also absorbing vibrations, and taking up less space.", Arrays.asList("RGB Backlighting", "Customizable with NGENUITY Software", "Tap-to-Mute Sensor with LED indicator", "Cardioid, Omnidirectional"), Arrays.asList("Black"), Arrays.asList("https://i.imgur.com/8Awowax.jpg", "https://i.imgur.com/9z6gQFg.jpg", "https://i.imgur.com/XAcElEH.jpg", "https://i.imgur.com/sDtbuZk.jpg", "https://i.imgur.com/RRr1dGo.jpg"), 69.99, 100);
//
//            Component headphoneHyperX1 = new Component("Headphone", "HyperX", "Cloud III - Gaming Headset", "The HyperX Cloud III is an evolution of our legendary Cloud II, which is known for its comfort, sound quality and durability. With plush HyperX signature memory foam in the headband and ear cushions, it provides a comfortable fit perfect for long gaming sessions. It also features new, retuned 53mm drivers that are angled for an optimal listening experience.", Arrays.asList("HyperX Signature Comfort and Durability", "Angled 53mm Drivers, Tuned for Impeccable Audio", "Crystal-Clear 10mm microphone, noise-cancelling, with LED mic-mute indicator", "Multiplatform Compatible with 3.5mm, USB-C, and USB-A\n"), Arrays.asList("Black-Red", "Black"), Arrays.asList("https://i.imgur.com/LNedJ4a.jpg", "https://i.imgur.com/uZpsZCB.jpg", "https://i.imgur.com/M13pQBt.jpg", "https://i.imgur.com/B1sV3Lx.jpg", "https://i.imgur.com/sv5Kom6.jpg"), 99.99, 100);
//
//            Component headphoneHyperX2 = new Component("Headphone", "HyperX", "Cloud Stinger - Comfortable Gaming Headset for PS5 and PS4", "HyperX Cloud Stinger™ is an Official PlayStation® Licensed headset, perfect for PS5™ and PS4™ gamers looking for comfort, superior sound quality, and convenience. It’s lightweight and features signature HyperX memory foam for legendary comfort during marathon gaming sessions. Its 90° rotating ear cups can rest comfortably around your neck during breaks. 50mm directional drivers pump out high-quality sound with pinpoint audio precision to keep you immersed in gaming.", Arrays.asList("Official PlayStation® licensed headset", "Lightweight with 90-degree rotating ear cups", "HyperX Signature comfort and durability", "Swivel-to-mute noise-cancelling mic"), Arrays.asList("Black-Blue"), Arrays.asList("https://i.imgur.com/icgxOgs.jpg", "https://i.imgur.com/cQCsd4Z.jpg", "https://i.imgur.com/6biD1U2.jpg", "https://i.imgur.com/FGai1E9.jpg", "https://i.imgur.com/p06upHJ.jpg"), 49.99, 100);
//
//
//            componentRepository.save(mouseHyperX1);
//            componentRepository.save(mouseHyperX2);
//            componentRepository.save(microphoneHyperX1);
//            componentRepository.save(microphoneHyperX2);
//            componentRepository.save(keyboardHyperX1);
//            componentRepository.save(keyboardHyperX2);
//            componentRepository.save(headphoneHyperX1);
//            componentRepository.save(headphoneHyperX2);
//
//            Component microphoneLogitech1 = new Component("Microphone", "Logitech", "Yeti GX", "Yeti GX is a premium RGB gaming mic powered by LIGHTSYNC and designed specifically for game streamers. Combining a custom dynamic capsule with advanced software, this USB microphone rejects noise and key clicks, improves your sound and ensures consistent high-quality audio while you stream.", Arrays.asList("Lightsync RGB","Dynamic Supercardoid Mic Capsule", "Broadcast-Quality Audio"), Arrays.asList("Black"), Arrays.asList("https://i.imgur.com/aH09N0Z.jpg", "https://i.imgur.com/cmIqoRx.jpg", "https://i.imgur.com/35Ic2Jd.jpg", "https://i.imgur.com/wZnMnen.jpg", "https://i.imgur.com/8cTx7ZB.jpg"), 149.99, 100);
//
//            Component microphoneLogitech2 = new Component("Microphone", "Logitech", "BLUE SONA", "Blue Sona is a new kind of XLR dynamic microphone designed for game streamers and podcasters. With ClearAmp technology, an innovative capsule and a modern, camera-ready aesthetic, Blue Sona brings out the best in every voice and creates a true studio-quality stream experience.", Arrays.asList("Dual-Diaphragm Mic Capsule","Internal Shockmount", "Supercardoid and Super Quiet"), Arrays.asList("White"), Arrays.asList("https://i.imgur.com/FwqLn1V.jpg", "https://i.imgur.com/lrPooQw.jpg", "https://i.imgur.com/4TGdGME.jpg", "https://i.imgur.com/mAwBAsM.jpg", "https://i.imgur.com/j4Izu5R.jpg"), 349.99, 100);
//
//            Component mouseLogitech1 = new Component("Mouse", "Logitech", "G705", "From the Aurora Collection, G705 Wireless Gaming Mouse is contoured for comfort and control with an intentional design to be inclusive of smaller hands. Features gaming-grade LIGHTSPEED wireless, LIGHTSYNC RGB, and advanced gaming technology.", Arrays.asList("Shaped For Comfort","Wireless", "Compact and Power-Packed"), Arrays.asList("White"), Arrays.asList("https://i.imgur.com/tb3qOzY.jpg", "https://i.imgur.com/knOr4Jf.jpg", "https://i.imgur.com/pTPg8lp.jpg"), 199.99, 100);
//
//            Component mouseLogitech2 = new Component("Mouse", "Logitech", "G903", "Ultimate gaming mouse. Ultralow-latency LIGHTSPEED wireless. HERO 25K sensor achieves sub-micron tracking. Ambidextrous design. Hyper-fast scrollwheel. POWERPLAY compatible for continuous wireless charging.", Arrays.asList("Ambidextrous - Up to 11 Buttons","Pro-Grade Wireless", "1:1 TRACKING, 25,600 MAX DPI"), Arrays.asList("Black"), Arrays.asList("https://i.imgur.com/CY2AP2O.jpg", "https://i.imgur.com/rgMOzYe.jpg", "https://i.imgur.com/aWlfKAk.jpg", "https://i.imgur.com/Dm6kLOf.jpg", "https://i.imgur.com/QWAXYpJ.jpg"), 129.99, 100);
//
//            Component keyboardLogitech1 = new Component("Keyboard", "Logitech", "PRO X TKL", "A championship-trusted wireless gaming keyboard designed for the highest levels of competitive play. Designed with pros and engineered to win.", Arrays.asList("RGB lighting with LIGHTSYNC","NEW Game Mode Lock function", "Designed for Pros to Win Championships"), Arrays.asList("Pink", "Black", "White"), Arrays.asList("https://i.imgur.com/5e0dnBK.jpg", "https://i.imgur.com/vAzp7jX.jpg", "https://i.imgur.com/qbIdqRW.jpg", "https://i.imgur.com/b4Xbu26.jpg", "https://i.imgur.com/5ETsoyY.jpg"), 199.99, 100);
//
//            Component keyboardLogitech2 = new Component("Keyboard", "Logitech", "G413", "Designed for advanced performance with the right feature set. The bladelike chassis is made of high-strength aluminum alloy with USB passthrough, backlit keys, Romer-G tactile mechanical switches, and more.", Arrays.asList("Precision Key Lighting","Aircraft-Grade Aluminium Alloy", "Advanced Gaming Features"), Arrays.asList("Silver", "Black"), Arrays.asList("https://i.imgur.com/8b5WKNU.jpg", "https://i.imgur.com/4Ves7Js.jpg", "https://i.imgur.com/CQm6O6c.jpg", "https://i.imgur.com/KFH0x3x.jpg"), 69.99, 2);
//
//            Component headphoneLogitech1 = new Component("Headphone", "Logitech", "PRO X 2 LIGHTSPEED", "Designed with pros. Engineered to win. PRO X 2 LIGHTSPEED headset features pro-grade sound, LIGHTSPEED wireless, and supreme comfort for the highest levels of competition. Hear every footstep, action, and pin pull with the immersive soundscape enabled by graphene drivers.", Arrays.asList("50mm Graphene Drivers","Lightspeed Wireless", "6mm Cardioid Microphone"), Arrays.asList("Pink", "Black", "White"), Arrays.asList("https://i.imgur.com/RcBsyv4.jpg", "https://i.imgur.com/TRNCiAj.jpg", "https://i.imgur.com/HT8DJPY.jpg", "https://i.imgur.com/1DQDm1N.jpg", "https://i.imgur.com/yskh36H.jpg"), 249.99, 100);
//
//            Component headphoneLogitech2 = new Component("Headphone", "Logitech", "Logitech FITS", "Logitech G FITS mold to give you a personal fit. No matter what size your ears are. Whether you like to be immersed in PC, mobile or console games. And no matter what hair styles or hats you like to wear, FITS create an individualized experience that adapts to you and how you like to play.", Arrays.asList("Pro-Grade Lightspeed and Low Latency Bluetooth Connectivity","Lightform Moulding with Patented LIGHTFORM technology", "Four Built-In Beamforming Microphones."), Arrays.asList("Black", "White"), Arrays.asList("https://i.imgur.com/hcllCUT.jpg", "https://i.imgur.com/qqCaTku.jpg", "https://i.imgur.com/Hy3iYtm.jpg", "https://i.imgur.com/B521ToP.jpg", "https://i.imgur.com/T4gWElI.jpg"), 229.99, 100);
//
//
//            componentRepository.save(microphoneLogitech1);
//            componentRepository.save(microphoneLogitech2);
//            componentRepository.save(mouseLogitech1);
//            componentRepository.save(mouseLogitech2);
//            componentRepository.save(keyboardLogitech1);
//            componentRepository.save(keyboardLogitech2);
//            componentRepository.save(headphoneLogitech1);
//            componentRepository.save(headphoneLogitech2);
//
//            Component headphoneAstro1 = new Component("Headphone", "Astro", "Astro A50", "Experience the performance and sound of ASTRO Audio V2 with the convenience and freedom that comes without wires. The A50 Wireless Headset for Xbox, PlayStation, and PC/Mac delivers top-of-the-line acoustics, ergonomics, and durability that professional gamers demand with the revolutionary, iconic ASTRO Gaming design. Experience Absolute Immersion with the A50 Wireless + Base Station.", Arrays.asList("Built-In MixAmp","Flip to Mute Microphone", "Dolby Audio"), Arrays.asList("Black-Gold", "Black-Grey"), Arrays.asList("https://i.imgur.com/sj2MMf7.jpg", "https://i.imgur.com/TYFEUJu.jpg", "https://i.imgur.com/f99oujM.jpg", "https://i.imgur.com/3bmcX1G.jpg", "https://i.imgur.com/YQ8kCiH.jpg"), 299.99, 100);
//
//            Component headphoneAstro2 = new Component("Headphone", "Astro", "Astro A20", "The A20 Wireless Gen 2 is designed to give you freedom on several levels. Its 2.4 GHz signal provides an extended wireless range and with the flip-to-mute microphone you can easily choose if you want to be heard or not. Additionally, the headset’s long-lasting comfort and battery life allow you to game through those marathon sessions. You can even add an extra USB transmitter for PlayStation 5 or Xbox Series X for an expanded console compatibility, which means you never have to switch headsets again. ", Arrays.asList("15M Wireless Range","Tuned with Astro Audio V2", "15+ Hours Battery Life"), Arrays.asList("Blue-White", "Green-White"), Arrays.asList("https://i.imgur.com/LaCiuFW.jpg", "https://i.imgur.com/ROg1zwN.jpg", "https://i.imgur.com/EbMqCO7.jpg", "https://i.imgur.com/hTnGr0A.jpg", "https://i.imgur.com/wyVH97x.jpg"), 199.99, 100);
//
//            Component mouseZowie1 = new Component("Mouse", "Zowie", "EC3-CW Wireless Mouse For Esports", "EC's trademark shape is a very widely used option for players who prefer asymmetrical ergo design.The top shell provides a natural curve for your hand with a relaxed support to your palm and fingers, providing more stability when moving horizontally. EC's classic shape has not-too-much palm contact and can accommodates different grip styles with comfortable posture. Reduce the fatigue of flexor carpi ulnaris muscle (FCU) when doing fast clicking", Arrays.asList("Wireless design with enhanced receiver","Asymmetrical ergonomic design with shorter overall length", "Reduced weight; 24-step scroll wheel", "Driverless; plug and play"), Arrays.asList("Black"), Arrays.asList("https://i.imgur.com/MQ0VexU.jpg", "https://i.imgur.com/klqnss5.jpg", "https://i.imgur.com/m0gEiaN.jpg", "https://i.imgur.com/cy4KQsC.jpg", "https://i.imgur.com/hy15ATm.jpg"), 149.99, 100);
//
//            Component mouseZowie2 = new Component("Mouse", "Zowie", "EC1-CW Wireless Mouse For Esports", "EC's trademark shape is a very widely used option for players who prefer asymmetrical ergo design.The top shell provides a natural curve for your hand with a relaxed support to your palm and fingers, providing more stability when moving horizontally. EC's classic shape has not-too-much palm contact and can accommodates different grip styles with comfortable posture. Reduce the fatigue of flexor carpi ulnaris muscle (FCU) when doing fast clicking", Arrays.asList("Wireless design with enhanced receiver","Asymmetrical ergonomic design with shorter overall length", "Reduced weight; 24-step scroll wheel", "Driverless; plug and play"), Arrays.asList("Black"), Arrays.asList("https://i.imgur.com/2ckauQG.jpg", "https://i.imgur.com/Ps6VHzY.jpg", "https://i.imgur.com/biMWDCJ.jpg", "https://i.imgur.com/XXM7jud.jpg", "https://i.imgur.com/dnBF4Tw.jpg"), 129.99, 100);
//
//            Component keyboardGamdia1 = new Component("Keyboard", "Gamdia", "HERMES P2", "The HERMES P2 is an optical-mechanical gaming keyboard with all the features of a premium mechanical keyboard. Featuring the all-new speedy and responsive optical gaming switches, 16.8 million RGB backlit, a solid metal backplate and a comfortable ergonomic wrist rest. The keyboard features a solid metal faceplate for lightweight stability and a built-in ergonomic wrist rest for maximum comfort.", Arrays.asList("Anti-ghosting with N-key rollover", "Metal back plating Mechanical Keyboard", "Ergonomic Wrist Rest", "GAMDIAS Certified Mechanical Optical Switches"), Arrays.asList("Black"), Arrays.asList("https://i.imgur.com/bYQbd7m.jpg", "https://i.imgur.com/lLEePcA.jpg", "https://i.imgur.com/FsVI2Je.jpg", "https://i.imgur.com/EmSc9vn.jpg"), 149.99, 100);
//
//            Component keyboardGamdia2 = new Component("Keyboard", "Gamdia", "HERMES M6", "SLEEK. PRECISE. DOMINANT. The HERMES M6 is a premium mechanical keyboard with a stylish posh metallic-textured brush-up backplate adjoined by two LED streaming lights matched with efficient and tactile feedback on each keystroke to help you get the job done. Bring vivid and brilliant illumination to the battlefield with seven built-in lighting effects and six color backlighting, along with two sky blue LED streaming lights on both wings of the keyboard to keep you in rhythm.", Arrays.asList("Multi colors backlights", "GAMDIAS Certified Mechanical Switches", "Ergonomic design", "Aluminum bezel"), Arrays.asList("White"), Arrays.asList("https://i.imgur.com/xdNiNh4.jpg", "https://i.imgur.com/hgqWjmB.jpg", "https://i.imgur.com/qtaCNZ5.jpg", "https://i.imgur.com/l11IPeT.jpg"), 129.99, 1);
//
//            Component microphoneRode1= new Component("Microphone", "Rode", "PodMic USB", "The PodMic USB is an ultra-versatile dynamic microphone ideal for podcasting, streaming, gaming, voice overs, and other speech applications for content creation. It delivers rich, full-bodied sound and features both an XLR and USB output for connecting to an audio interface or mixer like a classic broadcast microphone, or directly to a computer for plug-and-play recording.", Arrays.asList("Ultra-low-noise, high-gain Revolution Preamp", "Analog XLR and digital USB-C connectivity", "Fully compatible with Windows and Mac computers, and iOS and Android devices", "Broadcast-quality dynamic microphone"), Arrays.asList("Black"), Arrays.asList("https://i.imgur.com/AwCR6yo.jpg", "https://i.imgur.com/ddk5VoH.jpg", "https://i.imgur.com/rK0EJkM.jpg", "https://i.imgur.com/IFtcvlT.jpg", "https://i.imgur.com/Stm8QZN.jpg"), 199.99, 100);
//
//
//            componentRepository.save(headphoneAstro1);
//            componentRepository.save(headphoneAstro2);
//            componentRepository.save(mouseZowie1);
//            componentRepository.save(mouseZowie2);
//            componentRepository.save(keyboardGamdia1);
//            componentRepository.save(keyboardGamdia2);
//            componentRepository.save(microphoneRode1);
//
//            Buy buyPrueba = new Buy(2,120.0);
//            Buy buyPrueba2 = new Buy(4,240.0);
//            Ticket ticketPrueba = new Ticket("2",120.0, LocalDateTime.now(), BuyType.DEBIT);
//            Ticket ticketprueba2 = new Ticket("6775",240.0, LocalDateTime.now(), BuyType.CREDIT);
//
//            julianBrunelli.addTicket(ticketPrueba);
//            ticketRepository.save(ticketPrueba);
//
//            ticketPrueba.addBuy(buyPrueba);
//            buyRepository.save(buyPrueba);
//
//            mouseHyperX1.addBuy(buyPrueba);
//            buyRepository.save(buyPrueba);
//
//            Client gabrielBarbera = new Client("GabrielBarbera@outlook.com", "1234", 20056784, 1925, "36 e/ 123 y 124", "115351458");
//            clientRepository.save(gabrielBarbera);
//
//            gabrielBarbera.addTicket(ticketprueba2);
//            ticketRepository.save(ticketprueba2);
//
//            ticketprueba2.addBuy(buyPrueba2);
//            buyRepository.save(buyPrueba2);
//
//            mouseHyperX1.addBuy(buyPrueba2);
//            buyRepository.save(buyPrueba2);
//
//            Client maximilianoLedesma = new Client("MaximilianoLedesma@outlook.com", "1234", 21056784, 1925, "36 e/ 123 y 124", "115351456");
//            clientRepository.save(maximilianoLedesma);
//
//            Client alanisLobato = new Client("AlanisLobato@outlook.com", "1234", 43417900, 1925, "36 e/ 123 y 124", "2216204142");
//            clientRepository.save(alanisLobato);
//
//            Client darylBaptie = new Client("DarylBaptie@outlook.com", "1234", 35042684, 1925, "36 e/ 123 y 124", "114972353");
//            clientRepository.save(darylBaptie);
        };
    }
}